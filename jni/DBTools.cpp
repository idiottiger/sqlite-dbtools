#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>
#include <vector>

#include "sqlite/sqlite3.h"
#include "DBTools.h"

#define  LOGD(TAG,...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define  LOGI(TAG,...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define  LOGW(TAG,...)  __android_log_print(ANDROID_LOG_WARN,TAG,__VA_ARGS__)
#define  LOGE(TAG,...)  __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

#define TAG "pkg.DBTools"
#define SPLIT_STRING ":"
#define SQL_GET_TABLES "SELECT name FROM sqlite_master WHERE type='table'"

char *jstring2cstring(JNIEnv *env, jstring str);
jstring cstring2jstring(JNIEnv *env, const char *pStr);

using namespace std;

JNIEXPORT jint JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeGetTableNames
(JNIEnv *env, jclass cls, jstring db_path, jobject out_buffer)
{
    int result_code = RESULT_OK;
    char *c_db_path = jstring2cstring(env, db_path);
    LOGI(TAG, "invoke nativeGetTableNames: db path[%s]", c_db_path);
    sqlite3 *database;
    int ret;
    if ((ret = sqlite3_open(c_db_path, &database)) == SQLITE_OK)
    {
        sqlite3_stmt *statement;
        ret = sqlite3_prepare_v2(database, SQL_GET_TABLES, -1, &statement, NULL);

        //check
        if (ret == SQLITE_OK)
        {
            //query out
            std::vector<char *> table_name_list;
            int table_string_length = 0;
            char *out_buffer_bytes = (char *)env->GetDirectBufferAddress(out_buffer);
            bool is_cpy = true;
            while (sqlite3_step(statement) == SQLITE_ROW)
            {
                char *table_name = (char *)sqlite3_column_text(statement, 0);
                LOGD(TAG, "table name:[%s]", table_name);
                if (is_cpy)
                {
                    strcpy(out_buffer_bytes, table_name);
                }
                else
                {
                    strcat(out_buffer_bytes, table_name);
                }
                strcat(out_buffer_bytes, SPLIT_STRING);
                is_cpy = false;
            }
            sqlite3_finalize(statement);
            sqlite3_close(database);
        }
        else
        {
            LOGE(TAG, "Error query db: [%s], sql:[%s], error code:[%d]", c_db_path, SQL_GET_TABLES, ret);
            result_code = RESULT_SQL_RUN_ERROR;
        }
    }
    else
    {
        LOGE(TAG, "Error open db: [%s], error code:[%d]", c_db_path, ret);
        result_code = RESULT_DB_OPEN_ERROR;
    }

    free((void *)c_db_path);
    return result_code;
}


JNIEXPORT jint JNICALL Java_pkg_id2_dbtools_library_DBTools_nativeGetTableData
(JNIEnv *env, jclass cls, jstring db_path, jstring table_name, jobject out_buffer)
{
    return 0;
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
