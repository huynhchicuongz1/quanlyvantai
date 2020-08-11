package tdc.edu.vn.doan.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tdc.edu.vn.doan.TableDatabase.Table_PhanCong;
import tdc.edu.vn.doan.TableDatabase.Table_TaiXe;
import tdc.edu.vn.doan.TableDatabase.Table_Tinh;
import tdc.edu.vn.doan.TableDatabase.Table_Tuyen;
import tdc.edu.vn.doan.TableDatabase.Table_Xe;


public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, "QUANLYVANTAI_V1_1",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao bang tai xe
        String sqlTaiXe="CREATE TABLE "+ Table_TaiXe.TABLE_NAME + " ("
                + Table_TaiXe.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Table_TaiXe.KEY_MATAIXE + " TEXT,"
                + Table_TaiXe.KEY_TENTAIXE + " TEXT,"
                + Table_TaiXe.KEY_DIACHI + " TEXT,"
                + Table_TaiXe.KEY_NGAYSINH + " TEXT,"
                + Table_TaiXe.KEY_CHUKI+" BLOB );";

        db.execSQL(sqlTaiXe);

        String sqlTinh ="CREATE TABLE "+ Table_Tinh.TABLE_NAME + " ("
                + Table_Tinh.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Table_Tinh.KEY_MATINH + " TEXT ,"
                + Table_Tinh.KEY_TENTINH + " TEXT );";
        db.execSQL(sqlTinh);



        String sqlXeTai ="CREATE TABLE "+ Table_Xe.TABLE_NAME + " ("
                + Table_Xe.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Table_Xe.KEY_MAXE + " TEXT ,"
                + Table_Xe.KEY_TENXE + " TEXT ,"
                + Table_Xe. KEY_NAMSX + " TEXT ,"
                + Table_Xe.KEY_TAIXE_ID + " INTEGER );";

        db.execSQL(sqlXeTai);

        String sqlTuyen ="CREATE TABLE "+ Table_Tuyen.TABLE_NAME + " ("
                + Table_Tuyen.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Table_Tuyen.KEY_MATUYEN + " TEXT,"
                + Table_Tuyen.KEY_GIA + " INTEGER ,"
                + Table_Tuyen.KEY_TENTUYEN + " TEXT );";

        db.execSQL(sqlTuyen);

        String sqlPhanCong ="CREATE TABLE "+ Table_PhanCong.TABLE_NAME + " ("
                + Table_PhanCong.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Table_PhanCong.KEY_NXPHIEU + " TEXT,"
                + Table_PhanCong.KEY_NXPHAT + " TEXT,"
                + Table_PhanCong.KEY_ID_TUYEN + " INTEGER ,"
                + Table_PhanCong.KEY_ID_XE + " INTEGER );";
        db.execSQL(sqlPhanCong);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
