package tdc.edu.vn.test.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "QuanLyVanTai", null, 1);
    }

    private static final String CREATE_TABLE_TAIXE = "CREATE TABLE TAI_XE(MaTX text, TenTX text, NgaySinh text, DiaChi text)";
    private static final String CREATE_TABLE_XE = "CREATE TABLE XE(MaXe text, TenXe text, NamSX text, MaTX text)";
    private static final String CREATE_TABLE_TUYEN = "CREATE TABLE TUYEN(MaTuyen text, TenTuyen text, Gia text)";
    private static final String CREATE_TABLE_TINH = "CREATE TABLE TINH (MaTinh text, TenTinh text)";
    private static final String CREATE_TABLE_PHANCONG = "CREATE TABLE PHANCONG(sophieu text, ngayxuatphieu text, ngayxuatphat text, tuyen text, xe text)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PHANCONG);
        db.execSQL(CREATE_TABLE_TAIXE);
        db.execSQL(CREATE_TABLE_TUYEN);
        db.execSQL(CREATE_TABLE_TINH);
        db.execSQL(CREATE_TABLE_XE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
