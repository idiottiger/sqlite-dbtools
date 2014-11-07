package pkg.id2.dbtools.library;

import android.content.Context;
import android.util.Log;
import dbtools_proto.Tablenames;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DBTools {

    static {
        //System.loadLibrary("stlport_shared");
        System.loadLibrary("dbtools");
    }

    private static final String TAG = "DBTOOLS";

    private static final String DEFAULT_DB_PATH_FMT = "/data/data/%s/files/databases/%s";

    public static final int RESULT_DB_NOT_FOUND = -1024;
    public static final int RESULT_DB_OPEN_ERROR = RESULT_DB_NOT_FOUND + 1;
    public static final int RESULT_SQL_RUN_ERROR = RESULT_DB_OPEN_ERROR + 1;
    public static final int RESULT_TABLE_NOT_FOUND = RESULT_SQL_RUN_ERROR + 1;

    private static final String PROTO_OUT_TABLE_NAMES = "TN";
    private static final String PROTO_OUT_TABLE_COLUMNS = "TC";
    private static final String PROTO_OUT_TABLE_ROWDATAS = "TRD";
    private static final String PROTO_OUT_TABLE_QUERYDATAS = "TQD";

    private static native int nativeGetErrorCode();

    private static native void nativeInit(String outFolderPath);

    private static native void nativeRelease();

    private static native int nativeGetTableNames(String databasePath);

    private static native int nativeGetTableColumnNames(String databasePath, String tableName);

    private static native int nativeGetTableRowDatas(String databasePath, String tableName, int rowStart, int rowCounts);

    private static native int nativeGetTableQueryDatas(String databasePath, String sql);

    private static String sOutFolderPath;

    public static void initLibrary(Context context) {
        sOutFolderPath = context.getFilesDir().getAbsolutePath();
        nativeInit(sOutFolderPath);
    }

    public static List<String> getTableNames(Context context, String databaseName) {
        final String databasePath = String.format(DEFAULT_DB_PATH_FMT, context.getPackageName(), databaseName);
        final int result = nativeGetTableNames(databasePath);
        final List<String> tableNameList = new ArrayList<String>();
        if (result < 0) {
            Log.e(TAG, "get table names from:" + databasePath + ", error:" + result);
        } else {
            final String fdName = sOutFolderPath + "/" + PROTO_OUT_TABLE_NAMES;

            try {
                Tablenames.TableNames tableNames = Tablenames.TableNames.parseFrom(new FileInputStream(fdName));
                final int size = tableNames.getNameCount();
                for (int i = 0; i < size; i++) {
                    tableNameList.add(tableNames.getName(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tableNameList;
    }

}
