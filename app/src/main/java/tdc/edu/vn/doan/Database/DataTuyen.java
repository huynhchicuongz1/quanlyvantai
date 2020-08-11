package tdc.edu.vn.doan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.Model.Tuyen;
import tdc.edu.vn.doan.TableDatabase.Table_Tinh;
import tdc.edu.vn.doan.TableDatabase.Table_Tuyen;


public class DataTuyen {
    DatabaseHandler handler;

    public DataTuyen(Context context) {
        this.handler = new DatabaseHandler(context);
    }


    public void them(Tuyen tuyen)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_Tuyen.KEY_MATUYEN, tuyen.getMaTuyen());
        values.put(Table_Tuyen.KEY_TENTUYEN, tuyen.getTenTuyen());
        values.put(Table_Tuyen.KEY_GIA, tuyen.getGia());
        db.insert(Table_Tuyen.TABLE_NAME,null,values);
    }
    public ArrayList<Tuyen> getAll(){
        SQLiteDatabase db=handler.getReadableDatabase();
        ArrayList<Tuyen> list =new ArrayList<>();
        String sql="SELECT * from "+ Table_Tuyen.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String ma =cursor.getString(1);
                String ten=cursor.getString(3);
                int gia=cursor.getInt(2);
                list.add(new Tuyen(id,ma,ten,gia));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void xoa(Tuyen tuyen){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_Tuyen.TABLE_NAME, Table_Tuyen.KEY_MATUYEN +"=?",new String[]{String.valueOf(tuyen.getMaTuyen())});
    }
    public int sua(Tuyen tuyen){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_Tuyen.KEY_TENTUYEN, tuyen.getTenTuyen());
        values.put(Table_Tuyen.KEY_GIA, tuyen.getGia());
        return db.update(Table_Tuyen.TABLE_NAME,values, Table_Tuyen.KEY_MATUYEN +"=?",new String[]{String.valueOf(tuyen.getMaTuyen())});
    }

}
