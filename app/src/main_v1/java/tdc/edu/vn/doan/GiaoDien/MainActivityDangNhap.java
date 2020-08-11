package tdc.edu.vn.doan.GiaoDien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import tdc.edu.vn.doan.R;

public class MainActivityDangNhap extends AppCompatActivity {
    EditText edit_user,edit_password;
    Button bt_login;
    private Button btnTaoTK;
    Animation  go_anim;
    boolean running = true;
    ImageView cat;

    // Tao tai khoan
    Intent intent;
    String ten, mk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);
        setControl();
        setEvent();
        Runnable runnable = new Runnable() {
            public void run() {
                final AnimationDrawable runningCat = (AnimationDrawable) cat.getDrawable();
                runningCat.start();
            }
        };

        Handler handler = new android.os.Handler();
        handler.postDelayed(runnable, 1000);
    }
    private void setControl(){
        edit_user=findViewById(R.id.edit_username);
        edit_password=findViewById(R.id.edit_password);
        bt_login=findViewById(R.id.bt_login);
        cat = (ImageView) findViewById(R.id.imgCat);
        btnTaoTK = (Button) findViewById(R.id.btnTaoTK);
    }
    private boolean isSuccess(String textUser,String textPassword){
        String user="123";
        String password="123";

        return user.equals(textUser) && password.equals(textPassword);
    }
    private void setEvent(){
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String getTextUser=edit_user.getText().toString();
//                String getPassUser=edit_password.getText().toString();
//                if(!isSuccess(getTextUser,getPassUser)){
//                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityDangNhap.this);
//                    builder.setTitle("Thông Báo");
//                    builder.setMessage("Đăng nhập thất bại mời nhập lại");
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                    builder.create().show();
//                }
//                else {
//                    Intent intent = new Intent(MainActivityDangNhap.this, MainActivityMenu.class);
//                    Bundle bundle=new Bundle();
//                    bundle.putString("username",getTextUser);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }

                if(edit_user.getText().length() != 0 && edit_password.getText().length() != 0) {
                    if(edit_user.getText().toString().equals(ten) && edit_password.getText().toString().equals(mk)){
                        Toast.makeText(MainActivityDangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        intent =  new Intent(MainActivityDangNhap.this, MainActivityMenu.class);
                        startActivity(intent);
                    }else if (edit_user.getText().toString().equals("1") && edit_password.getText().toString().equals("1")){
                        Toast.makeText(MainActivityDangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        intent =  new Intent(MainActivityDangNhap.this, MainActivityMenu.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivityDangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivityDangNhap.this, "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });
        go_anim = AnimationUtils.loadAnimation(this,R.anim.go_animation);

        cat.setAnimation(go_anim);

        btnTaoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTK();
            }
        });
    }

    // Hàm tạo tài khoản mới
    private void addTK(){
        final Dialog dialog = new Dialog(MainActivityDangNhap.this);
        dialog.setTitle("Hộp thoại xữ lý");
        dialog.setCancelable(false); // không cho nguoi dung thoat khoai hop thoai
        dialog.setContentView(R.layout.activity_create);
        final EditText edtTenND = (EditText) dialog.findViewById(R.id.edtTenNguoiDung);
        final EditText edtMK = (EditText) dialog.findViewById(R.id.edtMatKhau);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);
        Button btnDangKy = (Button) dialog.findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten = edtTenND.getText().toString().trim();
                mk = edtMK.getText().toString().trim();
                // Truyen lại from dang nhap
                edit_user.setText(ten);
                edit_password.setText(mk);
                dialog.cancel();

                Toast.makeText(MainActivityDangNhap.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
