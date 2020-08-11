package tdc.edu.vn.doan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.TableDatabase.Table_TaiXe;


public class DataTaiXe {
    DatabaseHandler handler;

    public DataTaiXe(Context context) {
        this.handler = new DatabaseHandler(context);
    }


    public void themTaiXe(TaiXe taiXe)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_TaiXe.KEY_MATAIXE, taiXe.getMaTX());
        values.put(Table_TaiXe.KEY_TENTAIXE, taiXe.getTenTX());
        values.put(Table_TaiXe.KEY_DIACHI, taiXe.getDiaChi());
        values.put(Table_TaiXe.KEY_NGAYSINH, taiXe.getNgaySinh());
        values.put(Table_TaiXe.KEY_CHUKI, taiXe.getImgSignature());
        db.insert(Table_TaiXe.TABLE_NAME,null,values);
    }
    public ArrayList<TaiXe> getAll(){
        SQLiteDatabase db=handler.getReadableDatabase();
        ArrayList<TaiXe> listTaiXe =new ArrayList<>();
        String sql="SELECT * from "+ Table_TaiXe.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String maTaiXe=cursor.getString(1);
                String ten=cursor.getString(2);
                String ngaySinh=cursor.getString(3);
                String diaChi=cursor.getString(4);
                byte[] chuKy=cursor.getBlob(5);
                listTaiXe.add(new TaiXe(id,maTaiXe,ten,ngaySinh,diaChi,chuKy));
            }while (cursor.moveToNext());
        }
        return listTaiXe;
    }


    public void xoaTaiXe(TaiXe taiXe){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_TaiXe.TABLE_NAME, Table_TaiXe.KEY_MATAIXE +"=?",new String[]{String.valueOf(taiXe.getMaTX())});
    }
    public int suaTaiXe(TaiXe taiXe){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(Table_TaiXe.KEY_TENTAIXE, taiXe.getTenTX());
        values.put(Table_TaiXe.KEY_DIACHI, taiXe.getDiaChi());
        values.put(Table_TaiXe.KEY_NGAYSINH, taiXe.getNgaySinh());
        values.put(Table_TaiXe.KEY_CHUKI, taiXe.getImgSignature());
        return db.update(Table_TaiXe.TABLE_NAME,values, Table_TaiXe.KEY_MATAIXE +"=?",new String[]{String.valueOf(taiXe.getMaTX())});
    }

}
