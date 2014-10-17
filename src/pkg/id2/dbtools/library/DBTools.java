package pkg.id2.dbtools.library;

import java.nio.ByteBuffer;

public class DBTools {

	static {
		System.loadLibrary("dbtools");
	}

	// default 4 M
	private static final ByteBuffer sByteBuffer = ByteBuffer.allocateDirect(4 * 1024 * 1024);

	private static final String DEFAULT_DB_PATH_FMT = "/data/data/%s/files/databases/%s";

	private static final String SPLIT_STRING = ":";

	public static final int RESULT_OK = 1;
	public static final int RESULT_DB_NOT_FOUND = RESULT_OK + 1;
	public static final int RESULT_DB_OPEN_ERROR = RESULT_DB_NOT_FOUND + 1;
	public static final int RESULT_SQL_RUN_ERROR = RESULT_DB_OPEN_ERROR + 1;
	public static final int RESULT_TABLE_NOT_FOUND = RESULT_SQL_RUN_ERROR + 1;

	public static synchronized String[] getTableNames(String pkgName, String dbName) {
		return getTableNames(String.format(DEFAULT_DB_PATH_FMT, pkgName, dbName));
	}

	public static synchronized String[] getTableNames(String dbPath) {
		sByteBuffer.rewind();
		final int result = nativeGetTableNames(dbPath, sByteBuffer);
		if (result == RESULT_OK) {
			final byte[] data = sByteBuffer.array();
			String dataString = new String(data, 0, 100);
			String[] names = dataString.split(SPLIT_STRING);
			return names;
		}
		return null;
	}

	private static native int nativeGetTableNames(String dbPath, ByteBuffer outBuffer);

	private static native int nativeGetTableData(String dbPath, String tableName, ByteBuffer outBuffer);

	public static class TableColumn {
		public static enum ColumnType {
			TYPE_INT, TYPE_STRING, TYPE_DOUBLE, TYPE_DATE
		}

		public String name;
		public ColumnType type;
	}

	public static final class TableRow {
		Object[] rowData;

		public int getInt(int index) {
			return (Integer) rowData[index];
		}

		public String getString(int index) {
			return (String) rowData[index];
		}

		public double getDouble(int index) {
			return (Double) rowData[index];
		}
	}

	public static class TableData {
		TableColumn[] columns;

	}
}
