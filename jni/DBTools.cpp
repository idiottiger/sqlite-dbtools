#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>
#include <vector>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include "sqlite/sqlite3.h"
#include "DBTools.h"
#include "proto/tablenames.pb.h"
#include "proto/tablecolumns.pb.h"

#define  LOGD(TAG,...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define  LOGI(TAG,...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define  LOGW(TAG,...)  __android_log_print(ANDROID_LOG_WARN,TAG,__VA_ARGS__)
#define  LOGE(TAG,...)  __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

#define TAG "DB_TOOLS"
#define SQL_GET_TABLES "SELECT name FROM sqlite_master WHERE type='table'"

// %s is the table name, the result like follow:
// 0|ID|INTEGER|1||1
// 1|AUDIO_PATH|TEXT|1|0|0
// 2|AUDIO_DURATION|INTEGER|0||0
//
#define FMT_SQL_GET_TABLE_COLUMNS "PRAGMA table_info(%s)"
#define PATH_NAME_MAX 256
#define SQL_MAX PATH_NAME_MAX

#define TYPE_INTEGER "INTEGER"
#define TYPE_TEXT "TEXT"
#define TYPE_REAL "REAL"
#define TYPE_BLOB "BLOB"

#define PROTO_OUT_TABLE_NAMES "TN"
#define PROTO_OUT_TABLE_COLUMNS "TC"
#define PROTO_OUT_TABLE_ROWDATAS "TRD"
#define PROTO_OUT_TABLE_QUERYDATAS "TQD"

int error_code;
char out_folder_path[PATH_NAME_MAX];

using namespace std;
using namespace dbtools_proto;

TableColumn::ColumnType get_column_type(const char *typestr);

void set_error_code(int code)
{
    error_code = code;
}

void reset_error_code()
{
    set_error_code(0);
}

int get_error_code()
{
    int result = error_code;
    reset_error_code();
    return result;
}

int get_out_proto_fd(const char *name)
{
    char file_path[PATH_NAME_MAX];
    sprintf(file_path, "%s/%s", out_folder_path, name);
    return open(file_path, O_RDWR | O_CREAT | O_TRUNC, S_IRWXU);
}

TableColumn::ColumnType get_column_type(const char *typestr)
{
    if (strcmp(typestr, TYPE_INTEGER))
    {
        return TableColumn::INTEGER;
    }
    else if (strcmp(typestr, TYPE_REAL))
    {
        return TableColumn::REAL;
    }
    else if (strcmp(typestr, TYPE_BLOB))
    {
        return TableColumn::BLOB;
    }
    else
    {
        return TableColumn::TEXT;
    }
}

/*
 * Class:     pkg_id2_dbtools_library_DBTools
 * Method:    nativeGetErrorCode
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeGetErrorCode
(JNIEnv *env, jclass cls)
{
    return get_error_code();
}

/*
 * Class:     pkg_id2_dbtools_library_DBTools
 * Method:    nativeInit
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeInit
(JNIEnv *env, jclass cls, jstring outFolderPath)
{
    char *path = jstring2cstring(env, outFolderPath);

    strcpy(out_folder_path, path);
    free((void *)path);
}

/*
 * Class:     pkg_id2_dbtools_library_DBTools
 * Method:    nativeRelease
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeRelease
(JNIEnv *env, jclass cls)
{

}

/*
 * Class:     pkg_id2_dbtools_library_DBTools
 * Method:    nativeGetTableNames
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeGetTableNames
(JNIEnv *env, jclass cls, jstring databasePath)
{
    reset_error_code();
    int result_code = 0;
    char *c_db_path = jstring2cstring(env, databasePath);
    LOGI(TAG, "invoke nativeGetTableNames: db path[%s]", c_db_path);
    sqlite3 *database;
    int ret;
    TableNames tableNamesProto;

    if ((ret = sqlite3_open(c_db_path, &database)) == SQLITE_OK)
    {
        sqlite3_stmt *statement;

        ret = sqlite3_prepare_v2(database, SQL_GET_TABLES, -1, &statement, NULL);

        //check
        if (ret == SQLITE_OK)
        {
            while (sqlite3_step(statement) == SQLITE_ROW)
            {
                char *table_name = (char *)sqlite3_column_text(statement, 0);
                tableNamesProto.add_name(table_name);                    
            }
            sqlite3_finalize(statement);
            sqlite3_close(database);
        }
        else
        {
            LOGE(TAG, "[GET TABLE NAMES] Error query db: [%s], sql:[%s], error code:[%d]", c_db_path, SQL_GET_TABLES, ret);
            result_code = RESULT_SQL_RUN_ERROR;
        }
    }
    else
    {
        LOGE(TAG, "[GET TABLE NAMES] Error open db: [%s], error code:[%d]", c_db_path, ret);
        result_code = RESULT_DB_OPEN_ERROR;
    }

    free((void *)c_db_path);

    if (result_code == 0)
    {
        int fd = get_out_proto_fd(PROTO_OUT_TABLE_NAMES);
        if (fd == -1)
        {
            result_code = RESULT_WRITE_ERROR;
        }
        else
        {
            tableNamesProto.SerializeToFileDescriptor(fd);
            close(fd);
        }
    }

    set_error_code(result_code);

    return result_code;
}

/*
 * Class:     pkg_id2_dbtools_library_DBTools
 * Method:    nativeGetTableColumnNames
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeGetTableColumnNames
(JNIEnv *env, jclass cls, jstring databasePath, jstring tableName)
{
    reset_error_code();
    int result_code = 0;
    char *c_db_path = jstring2cstring(env, databasePath);
    char *c_table_name = jstring2cstring(env, tableName);
    LOGI(TAG, "invoke nativeGetTableColumnNames: db path[%s]", c_db_path);
    sqlite3 *database;
    int ret;
    TableColumnList tableColumnList;
    if ((ret = sqlite3_open(c_db_path, &database)) == SQLITE_OK)
    {
        sqlite3_stmt *statement;
        char sql[SQL_MAX];
        sprintf(sql, FMT_SQL_GET_TABLE_COLUMNS, c_table_name);
        ret = sqlite3_prepare_v2(database, sql, -1, &statement, NULL);

        //check
        if (ret == SQLITE_OK)
        {
            while (sqlite3_step(statement) == SQLITE_ROW)
            {
                TableColumn *column = tableColumnList.add_column();
                int index = sqlite3_column_int(statement, 0);
                char *name = (char *)sqlite3_column_text(statement, 1);
                char *type_str = (char *)sqlite3_column_text(statement, 2);

                column->set_index(index);
                column->set_name(name);
                column->set_type(get_column_type(type_str));
            }
            sqlite3_finalize(statement);
            sqlite3_close(database);
        }
        else
        {
            LOGE(TAG, "[GET TABLE COLUMN NAMES] Error query db: [%s], sql:[%s], error code:[%d]", c_db_path, sql, ret);
            result_code = RESULT_SQL_RUN_ERROR;
        }
    }
    else
    {
        LOGE(TAG, "[GET TABLE COLUMN NAMES] Error open db: [%s], error code:[%d]", c_db_path, ret);
        result_code = RESULT_DB_OPEN_ERROR;
    }

    free((void *)c_db_path);
    free((void *)c_table_name);

    if (result_code == 0)
    {
        int fd = get_out_proto_fd(PROTO_OUT_TABLE_COLUMNS);
        if (fd == -1)
        {
            result_code = RESULT_WRITE_ERROR;
        }
        else
        {
            tableColumnList.SerializeToFileDescriptor(fd);
            close(fd);
        }
    }

    set_error_code(result_code);

    return result_code;
}

/*
 * Class:     pkg_id2_dbtools_library_DBTools
 * Method:    nativeGetTableRowDatas
 * Signature: (Ljava/lang/String;Ljava/lang/String;II)I
 */
JNIEXPORT jint JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeGetTableRowDatas
(JNIEnv *env, jclass cls, jstring databasePath, jstring tableName, jint rowStart, jint rowCounts)
{

}

/*
 * Class:     pkg_id2_dbtools_library_DBTools
 * Method:    nativeGetTableQueryDatas
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeGetTableQueryDatas
(JNIEnv *env, jclass cls, jstring databasePath, jstring sql)
{

}

char *jstring2cstring(JNIEnv *env, jstring str)
{
    const char *cstring = env->GetStringUTFChars(str, 0);
    char *result = (char *)malloc(strlen(cstring) * sizeof(char) + 1);
    strcpy(result, cstring);
    //release
    env->ReleaseStringUTFChars(str, cstring);
    return result;
}

jstring cstring2jstring(JNIEnv *env, const char *pStr)
{
    int        strLen    = strlen(pStr);
    jclass     jstrObj   = env->FindClass("java/lang/String");
    jmethodID  methodId  = env->GetMethodID(jstrObj, "<init>", "([BLjava/lang/String;)V");
    jbyteArray byteArray = env->NewByteArray(strLen);
    jstring    encode    = env->NewStringUTF("utf-8");

    env->SetByteArrayRegion(byteArray, 0, strLen, (jbyte *)pStr);

    return (jstring)(env->NewObject(jstrObj, methodId, byteArray, encode));
}
