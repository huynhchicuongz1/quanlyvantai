package tdc.edu.vn.doan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.doan.Model.Xe;
import tdc.edu.vn.doan.TableDatabase.Table_Xe;


public class DataXe {
    DatabaseHandler handler;

    public DataXe(Context context) {
        this.handler = new DatabaseHandler(context);
    }

    public void themXe(Xe xe)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_Xe.KEY_MAXE, xe.getMaXe());
        values.put(Table_Xe.KEY_TENXE, xe.getTenXe());
        values.put(Table_Xe.KEY_NAMSX,xe.getNamSX());
        values.put(Table_Xe.KEY_TAIXE_ID, xe.getIdTaiXe());
        db.insert(Table_Xe.TABLE_NAME,null,values);
    }
    public ArrayList<Xe> getAll(){
        SQLiteDatabase db=handler.getReadableDatabase();
        ArrayList<Xe> list =new ArrayList<>();
        String sql="SELECT * from "+ Table_Xe.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String ma =cursor.getString(1);
                String ten=cursor.getString(2);
                String nam=cursor.getString(3);
                int idTX=cursor.getInt(4);
                list.add(new Xe(id,ma,ten,nam,idTX));
            }while (cursor.moveToNext());
        }
        return list;
    }
//    public ArrayList<Xe> getAll(){
//        SQLiteDatabase db=handler.getReadableDatabase();
//        ArrayList<Xe> list =new ArrayList<>();
//        String sql="SELECT * from "+ Table_Xe.TABLE_NAME;
//        Cursor cursor=db.rawQuery(sql,null);
//        if(cursor.moveToFirst()){
//            do{
//                int id=cursor.getInt(0);
//                String ma =cursor.getString(1);
//                String ten=cursor.getString(2);
////                String nam=cursor.getString(3);
//                list.add(new Xe(id,ma,ten,"1",0));
//            }while (cursor.moveToNext());
//        }
//        return list;
//    }

    public void xoa(Xe xe){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_Xe.TABLE_NAME, Table_Xe.KEY_MAXE +"=?",new String[]{String.valueOf(xe.getMaXe())});
    }
    public int sua(Xe xe){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_Xe.KEY_TENXE, xe.getTenXe());
        values.put(Table_Xe.KEY_NAMSX, xe.getNamSX());
        values.put(Table_Xe.KEY_TAIXE_ID, xe.getIdTaiXe());
        return db.update(Table_Xe.TABLE_NAME,values, Table_Xe.KEY_MAXE +"=?",new String[]{String.valueOf(xe.getMaXe())});
    }

}
