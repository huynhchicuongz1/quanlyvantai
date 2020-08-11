package tdc.edu.vn.doan.GiaoDien;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    final int REQUEST_CODE_GALLERY = 999;
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

//        img_signature.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                createPopupDialog();
//            }
//        });
        img_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        ActivityTaiXe.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
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
                img_signature.setImageBitmap(null);
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
                txt_NgaySinh.setText(databaseTaiXe.getAll().get(index).getNgaySinh());
                txt_DiaChi.setText(databaseTaiXe.getAll().get(index).getDiaChi());
                if(databaseTaiXe.getAll().get(index).getImgSignature()!=null) {
                    Bitmap bitmapToImage = BitmapFactory.decodeByteArray(databaseTaiXe.getAll().get(index).getImgSignature(), 0, databaseTaiXe.getAll().get(index).getImgSignature().length);
                    img_signature.setImageBitmap(bitmapToImage);
                }
//                startActivity(new Intent(ActivityTaiXe.this,ActivityDetail.class));
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
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_signature.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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
        String diaChi=txt_DiaChi.getText().toString();
        byte[] img=imageViewToByte(img_signature);
        TaiXe device=new TaiXe(0,ma,ten,ngaySinh,diaChi,img);
        return device;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }



}