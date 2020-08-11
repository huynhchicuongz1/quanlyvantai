package tdc.edu.vn.doan.GiaoDien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import tdc.edu.vn.doan.R;

public class MainActivityDangNhap extends AppCompatActivity {
    EditText edit_user,edit_password;
    TextView txtVN, txtUS;
    Button bt_login;
    Animation  go_anim;
    boolean running = true;
    ImageView cat;
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
        txtVN = (TextView) findViewById(R.id.txtVN);
        txtUS = (TextView) findViewById(R.id.txtUS);
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
                String getTextUser=edit_user.getText().toString();
                String getPassUser=edit_password.getText().toString();
                if(!isSuccess(getTextUser,getPassUser)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityDangNhap.this);
                    builder.setTitle("Thông Báo");
                    builder.setMessage("Đăng nhập thất bại mời nhập lại");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create().show();
                }
                else {
                    Intent intent = new Intent(MainActivityDangNhap.this, MainActivityMenu.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("username",getTextUser);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
        go_anim = AnimationUtils.loadAnimation(this,R.anim.go_animation);

        cat.setAnimation(go_anim);
        txtVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("us");
            }
        });

        txtUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("en");
            }
        });
    }
    public void ganNgonNgu(String language) {
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(
                config,
                getBaseContext().getResources().getDisplayMetrics()
        );
        Intent intent = new Intent(MainActivityDangNhap.this, IconActivity.class);
        startActivity(intent);
    }
}
