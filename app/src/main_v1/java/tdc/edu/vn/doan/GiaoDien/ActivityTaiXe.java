package tdc.edu.vn.doan.GiaoDien;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import tdc.edu.vn.doan.Adapter.CustomAdapterTX;
import tdc.edu.vn.doan.Database.DataTaiXe;
import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.R;


public class ActivityTaiXe extends AppCompatActivity {
    ArrayList<TaiXe> dataTaiXe;
    RecyclerView recyclerViewTaiXe;
    EditText txt_maTX,txt_tenTX,txt_NgaySinh,txt_DiaChi;

    Button bt_them,bt_xoa,bt_sua,bt_clear,bt_finish;
    ImageButton img_signature;


    DataTaiXe databaseTaiXe;
    CustomAdapterTX adapterTX;
    int index=-1;
    PaintView paintView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__tx);
        setControl();
        setEvent();
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        databaseTaiXe=new DataTaiXe(this);
        dataTaiXe = databaseTaiXe.getAll();
        adapterTX =new CustomAdapterTX(R.layout.list_custom_taixe, dataTaiXe);
        recyclerViewTaiXe.setAdapter(adapterTX);

        img_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
            }
        });

        //button listener
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiXe taiXe =getTaiXe();
                if(isValid()) {
                    databaseTaiXe.themTaiXe(getTaiXe());
                    dataTaiXe.add(taiXe);
                    adapterTX.notifyDataSetChanged();
                }
            }
        });
        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index!=-1) {
                    databaseTaiXe.xoaTaiXe(dataTaiXe.get(index));
                    dataTaiXe.remove(index);
                    adapterTX.notifyItemRemoved(index);
                    index=-1;
                }else
                    Toast.makeText(ActivityTaiXe.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_maTX.setText("");
                txt_DiaChi.setText("");
                txt_NgaySinh.setText("");
                txt_tenTX.setText("");
                index=-1;
            }
        });
        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index!=-1) {
                    TaiXe taiXe =getTaiXe();
                    databaseTaiXe.suaTaiXe(getTaiXe());
                    dataTaiXe.set(index, taiXe);
                    adapterTX.notifyItemChanged(index);
                }else
                    Toast.makeText(ActivityTaiXe.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
            }
        });
        adapterTX.setListener(new CustomAdapterTX.Listener() {
            @Override
            public void onClick(int position) {
                index=position;
                txt_maTX.setText(databaseTaiXe.getAll().get(index).getMaTX());
                txt_tenTX.setText(databaseTaiXe.getAll().get(index).getTenTX());


                startActivity(new Intent(ActivityTaiXe.this,ActivityDetail.class));
            }
        });
    }
    //kiem tra xem du lieu dau vao co hop ly khong
    private boolean isValid(){
        if(this.txt_tenTX.getText().toString().equals("")) {
            txt_tenTX.setError("Bạn nên nhập đầy đủ tên Tài Xế");
            return false;
        }
        return true;
    }
    private Bitmap getSignature(){
        return paintView.getBitmap();
    }
    private byte[] getByteBitmap(){
        try {
            Bitmap photo = paintView.getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
            return bos.toByteArray();
        }catch (Exception e){
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    public void createPopupDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(ActivityTaiXe.this);
        View view=getLayoutInflater().inflate(R.layout.signature,null);
        paintView =view.findViewById(R.id.customSign);
        bt_finish=view.findViewById(R.id.bt_finish);

        builder.setView(view);
        final AlertDialog show= builder.show();
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_signature.setImageBitmap(getSignature());
                show.dismiss();
            }
        });


    }


    private void setControl() {
        txt_maTX=findViewById(R.id.txt_maTX);
        txt_tenTX=findViewById(R.id.txt_tenTX);
        txt_DiaChi=findViewById(R.id.txt_DiaChi);
        txt_NgaySinh=findViewById(R.id.txt_NgaySinh);



        bt_them=findViewById(R.id.bt_add);
        bt_xoa=findViewById(R.id.bt_remove);
        bt_sua=findViewById(R.id.bt_update);
        bt_clear=findViewById(R.id.bt_clear);

        img_signature=findViewById(R.id.bt_signature);

        recyclerViewTaiXe=findViewById(R.id.recyclerViewTaiXe);
        recyclerViewTaiXe.setLayoutManager(new LinearLayoutManager(this));

    }
    private TaiXe getTaiXe(){
        String ten=txt_tenTX.getText().toString();
        String ma=txt_maTX.getText().toString();
        String ngaySinh=txt_NgaySinh.getText().toString();
        String diaChi=txt_NgaySinh.getText().toString();
        byte[] img=getByteBitmap();
        TaiXe device=new TaiXe(0,ma,ten,ngaySinh,diaChi,img);
        return device;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }



}