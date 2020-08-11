package tdc.edu.vn.doan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.PhanCong;
import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.TableDatabase.Table_PhanCong;
import tdc.edu.vn.doan.TableDatabase.Table_Tinh;


public class DataPhanCong {
    DatabaseHandler handler;

    public DataPhanCong(Context context) {
        this.handler = new DatabaseHandler(context);
    }


    public void them(PhanCong phanCong)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_PhanCong.KEY_NXPHIEU, phanCong.getNgayXuatPhieu());
        values.put(Table_PhanCong.KEY_NXPHAT, phanCong.getNoiXuatPhat());
        values.put(Table_PhanCong.KEY_ID_TUYEN, phanCong.getId_tuyen());
        values.put(Table_PhanCong.KEY_ID_XE, phanCong.getId_xe());
        db.insert(Table_PhanCong.TABLE_NAME,null,values);
    }

    public ArrayList<PhanCong> getAll(){
        SQLiteDatabase db=handler.getReadableDatabase();
        ArrayList<PhanCong> list =new ArrayList<>();
        String sql="SELECT * from "+ Table_PhanCong.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int soPhieu=cursor.getInt(0);
                String ngayXuatPhieu =cursor.getString(1);
                String noiXuatPhat=cursor.getString(2);
                int id_tuyen=cursor.getInt(3);
                int id_xe=cursor.getInt(4);
                list.add(new PhanCong(soPhieu,ngayXuatPhieu,noiXuatPhat,id_tuyen,id_xe));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void xoa(PhanCong phanCong){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_PhanCong.TABLE_NAME, Table_PhanCong.KEY_ID +"=?",new String[]{String.valueOf(phanCong.getSoPhieu())});
    }
    public int sua(PhanCong phanCong){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_PhanCong.KEY_NXPHIEU, phanCong.getNgayXuatPhieu());
        values.put(Table_PhanCong.KEY_NXPHAT, phanCong.getNoiXuatPhat());
        values.put(Table_PhanCong.KEY_ID_TUYEN, phanCong.getId_tuyen());
        values.put(Table_PhanCong.KEY_ID_XE, phanCong.getId_xe());
        return db.update(Table_PhanCong.TABLE_NAME,values, Table_PhanCong.KEY_ID +"=?",new String[]{String.valueOf(phanCong.getSoPhieu())});
    }

}
