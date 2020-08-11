package tdc.edu.vn.test.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import tdc.edu.vn.test.GiaoDien.MainActivity_TaiXe;
import tdc.edu.vn.test.Model.TaiXe;

public class DatabaseTaiXe extends MainActivity_TaiXe {
    private DatabaseHelper dbHelper;

    public DatabaseTaiXe(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void themTaiXe(TaiXe taiXe) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("MaTX", taiXe.getMaTX());
        values.put("TenTX", taiXe.getTenTX());
        values.put("NgaySinh", taiXe.getNgaySinh());
        values.put("DiaChi", taiXe.getDiaChi());

        db.insert("TAI_XE", null, values);
    }
    public void SuaTaiXe(TaiXe taiXe) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenTX", taiXe.getTenTX());
        values.put("NgaySinh", taiXe.getNgaySinh());
        values.put("DiaChi", taiXe.getDiaChi());
        db.update("TAI_XE",values ,"MaTX='"+taiXe.getMaTX()+"'", null);
    }

    public void XoaTaiXe(TaiXe taiXe) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from TAI_XE where MaTX= '"+taiXe.getMaTX()+"'";
        db.execSQL(sql);
    }

    public ArrayList<TaiXe> layDL() {
        ArrayList<TaiXe> arrTaiXe = new ArrayList<>();
        String sql1 = "select * from TAI_XE";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql1, null);
        try {
            cursor.moveToFirst();
            do {
                TaiXe taiXe = new TaiXe();
                taiXe.setMaTX(cursor.getString(0));
                taiXe.setTenTX(cursor.getString(1));
                taiXe.setNgaySinh(cursor.getString(2));
                taiXe.setDiaChi(cursor.getString(3));
                arrTaiXe.add(taiXe);
            } while (cursor.moveToNext());
        }catch (Exception ex )
        {}
        return arrTaiXe;
    }
    public ArrayList<TaiXe> layDL(String maTx) {
        ArrayList<TaiXe> arrTaiXe = new ArrayList<>();
        String sql = "select * from TAI_XE where MaTX ='"+maTx+"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                TaiXe taiXe = new TaiXe();

                taiXe.setMaTX(cursor.getString(0));
                taiXe.setTenTX(cursor.getString(1));
                taiXe.setNgaySinh(cursor.getString(2));
                taiXe.setDiaChi(cursor.getString(3));

                arrTaiXe.add(taiXe);
            } while (cursor.moveToNext());
        }catch (Exception ex )
        {}


        return arrTaiXe;
    }
}
