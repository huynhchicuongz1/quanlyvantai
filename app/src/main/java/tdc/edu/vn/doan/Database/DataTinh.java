package tdc.edu.vn.doan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.TableDatabase.Table_Tinh;


public class DataTinh {
    DatabaseHandler handler;

    public DataTinh(Context context) {
        this.handler = new DatabaseHandler(context);
    }


    public void themTinh(Tinh tinh)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_Tinh.KEY_MATINH, tinh.getMaTinh());
        values.put(Table_Tinh.KEY_TENTINH, tinh.getTenTinh());
        db.insert(Table_Tinh.TABLE_NAME,null,values);
    }
    public ArrayList<Tinh> getAll(){
        SQLiteDatabase db=handler.getReadableDatabase();
        ArrayList<Tinh> listTinh =new ArrayList<>();
        String sql="SELECT * from "+ Table_Tinh.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String ma =cursor.getString(1);
                String ten=cursor.getString(2);
                listTinh.add(new Tinh(id,ma,ten));
            }while (cursor.moveToNext());
        }
        return listTinh;
    }

    public void xoaTinh(Tinh tinh){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_Tinh.TABLE_NAME, Table_Tinh.KEY_MATINH +"=?",new String[]{String.valueOf(tinh.getMaTinh())});
    }
    public int suaTinh(Tinh tinh){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(Table_Tinh.KEY_TENTINH, tinh.getTenTinh());
        return db.update(Table_Tinh.TABLE_NAME,values, Table_Tinh.KEY_MATINH +"=?",new String[]{String.valueOf(tinh.getMaTinh())});
    }

}
