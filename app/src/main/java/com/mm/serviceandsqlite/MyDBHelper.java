package com.mm.serviceandsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by o on 2/5/2015.
 */
public class MyDBHelper extends SQLiteOpenHelper{

    private final Context m_ctx;
    private static final String TAG              = "MyDBHelper";
    private static final String DATABASE_NAME    = "test.db";
    private static final int    DATABASE_VERSION = 1;
    public MyDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        m_ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS TESTING (INFO_ID TEXT,"
                + " INFO_TEXT TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static boolean isAnyInfoAvailable(Context ctx){
        boolean result = false;
        MyDBHelper dbh = null;
        SQLiteDatabase db = null;
        try {
            dbh = new MyDBHelper(ctx);
            db = dbh.getWritableDatabase();
            result = MyDBHelper.is_any_info_available(db);
        } catch (Throwable e) {
            Log.e(TAG, "isAnyInfoAvailable(): Caught - " + e.getClass().getName(), e);
        } finally {
            if (null != db)
                db.close();
            if (null != dbh)
                dbh.close();
        }
        return result;
    }

    public static boolean is_any_info_available(SQLiteDatabase db){
        boolean result = false;

        Cursor cInfo = db.rawQuery(
                "select INFO_ID from TESTING", null);
        if(cInfo != null)
        {
            if(cInfo.moveToFirst())
            {
                result = true;
            }
        }
        if(cInfo != null)
            cInfo.close();
        return result;
    }
}
