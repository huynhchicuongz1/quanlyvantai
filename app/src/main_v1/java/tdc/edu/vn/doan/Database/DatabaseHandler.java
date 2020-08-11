package tdc.edu.vn.doan.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tdc.edu.vn.doan.TableDatabase.Table_TaiXe;


public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, "QLVANTAI_V2",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang tai xe
        String sqlTaiXe="CREATE TABLE "+ Table_TaiXe.TABLE_NAME + " ("
                + Table_TaiXe.KEY_ID + " INTEGER PRIMARY KEY,"
                + Table_TaiXe.KEY_MATAIXE + " TEXT,"
                + Table_TaiXe.KEY_TENTAIXE + " TEXT,"
                + Table_TaiXe.KEY_DIACHI + " TEXT,"
                + Table_TaiXe.KEY_NGAYSINH + " TEXT,"
                + Table_TaiXe.KEY_CHUKI+" BLOB );";

        db.execSQL(sqlTaiXe);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
