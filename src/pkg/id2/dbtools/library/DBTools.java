package pkg.id2.dbtools.library;

public class DBTools {

    static {
        System.loadLibrary("dbtools");
    }

    private static final String DEFAULT_DB_PATH_FMT = "/data/data/%s/files/databases/%s";

    public static final int RESULT_DB_NOT_FOUND = -1024;
    public static final int RESULT_DB_OPEN_ERROR = RESULT_DB_NOT_FOUND + 1;
    public static final int RESULT_SQL_RUN_ERROR = RESULT_DB_OPEN_ERROR + 1;
    public static final int RESULT_TABLE_NOT_FOUND = RESULT_SQL_RUN_ERROR + 1;


    private static native int nativeGetErrorCode();

    private static native void nativeInit(String outFolderPath);

    private static native int nativeGetTableNames(String databasePath);

    private static native int nativeGetTableColumnNames(String databasePath, String tableName);

    private static native int nativeGetTableRowDatas(String databasePath, String tableName, int rowStart, int rowCounts);

    private static native int nativeGetTableQueryDatas(String databasePath, String sql);
}
