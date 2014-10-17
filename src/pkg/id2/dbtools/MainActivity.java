package pkg.id2.dbtools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import pkg.id2.dbtools.library.DBTools;
import pkg.id2.sqlitedbtools.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new File(getFilesDir().getAbsolutePath() + "/databases").mkdirs();
		copyAssetFile2Another(this, "test.sqlite", getFilesDir().getAbsolutePath() + "/databases/test");
		String[] names = DBTools.getTableNames(getPackageName(), "test");
		if (names != null) {
			for (String name : names) {
				System.out.println("t:" + name);
			}
		}

	}

	public static void copyAssetFile2Another(Context context, String assetFileName, String copyToPath) {
		File copyTo = new File(copyToPath);
		copyTo.delete();
		// cp
		try {
			BufferedInputStream bis = new BufferedInputStream(context.getAssets().open(assetFileName));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyTo), 16 * 1024);
			byte[] buffer = new byte[16 * 1024];
			int length;
			while ((length = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, length);
			}
			bos.flush();
			bos.close();
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
