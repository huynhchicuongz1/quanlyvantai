package tdc.edu.vn.test.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import tdc.edu.vn.test.GiaoDien.MainActivity;
import tdc.edu.vn.test.Model.PhanCong;

public class DatabasePhanCong extends MainActivity {
    private DatabaseHelper dbHelper;

    public DatabasePhanCong(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void themXe(PhanCong xt) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("sophieu", xt.getSoPhieu());
        values.put("ngayxuatphieu", xt.getNgayXuatPhieu());
        values.put("ngayxuatphat", xt.getNgayXuatPhat());
        values.put("tuyen", xt.getTuyen());
        values.put("xe", xt.getXe());

        db.insert("PHANCONG", null, values);
    }

    public void SuaXe(PhanCong xt) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ngayxuatphieu", xt.getNgayXuatPhieu());
        values.put("ngayxuatphat", xt.getNgayXuatPhat());
        values.put("tuyen", xt.getTuyen());
        values.put("xe", xt.getXe());
        db.update("PHANCONG",values ,"sophieu='"+xt.getSoPhieu()+"'", null);
    }

    public void XoaXe(PhanCong xt) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from PHANCONG where sophieu= '"+xt.getSoPhieu()+"'";
        db.execSQL(sql);
    }

    public void themDanhSachXe(ArrayList<PhanCong> arrPhanCong) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values;

        String sql="DROP TABLE PHANCONG";
        db.execSQL(sql);
        sql = "CREATE TABLE PHANCONG(sophieu text, ngayxuatphieu text, ngayxuatphat text, tuyen text, xe text)";
        db.execSQL(sql);

        int tam = arrPhanCong.size();
        for (int i = 0; i < tam; i++) {
            values = new ContentValues();
            PhanCong xt = arrPhanCong.get(i);
            Log.e("Dsd",xt.getNgayXuatPhat());
            values.put("sophieu", xt.getSoPhieu());
            values.put("ngayxuatphieu", xt.getNgayXuatPhieu());
            values.put("ngayxuatphat", xt.getNgayXuatPhat());
            values.put("tuyen", xt.getTuyen());
            values.put("xe", xt.getXe());
            db.insert("PHANCONG", null, values);
        }
    }



    public ArrayList<PhanCong> layDL() {
        ArrayList<PhanCong> arrPhanCong = new ArrayList<>();
        String sql = "select * from PHANCONG";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                PhanCong xt = new PhanCong();
                xt.setSoPhieu(cursor.getString(0));
                xt.setNgayXuatPhieu(cursor.getString(1));
                xt.setNgayXuatPhat(cursor.getString(2));
                xt.setTuyen(cursor.getString(3));
                xt.setXe(cursor.getString(4));

                arrPhanCong.add(xt);
            } while (cursor.moveToNext());
        }catch (Exception ex )
        {}


        return arrPhanCong;
    }

    public ArrayList<PhanCong> layDL(String soPhieu) {
        ArrayList<PhanCong> arrPhanCong = new ArrayList<>();
        String sql = "select * from PHANCONG where sophieu ='"+soPhieu+"'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                PhanCong xt = new PhanCong();
                xt.setSoPhieu(cursor.getString(0));
                xt.setNgayXuatPhieu(cursor.getString(1));
                xt.setNgayXuatPhat(cursor.getString(2));
                xt.setTuyen(cursor.getString(3));
                xt.setXe(cursor.getString(4));

                arrPhanCong.add(xt);
            } while (cursor.moveToNext());
        }catch (Exception ex )
        {}


        return arrPhanCong;
    }
}
