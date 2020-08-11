package tdc.edu.vn.doan.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import tdc.edu.vn.doan.R;

public class IconActivity extends AppCompatActivity {
    Animation top_anim,bottom_anim;
    TextView text_bt;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);
        setControl();
        setEvent();
        //Dùng cài đặt sau 5 giây màn hình tự chuyển
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(2000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent intent = new Intent(IconActivity.this, MainActivityDangNhap.class);
                    startActivity(intent);
                }
            }
        };

        bamgio.start();
    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }
    private void setControl(){
        text_bt = findViewById(R.id.text_bt);
        imageView2 = findViewById(R.id.imageView2);
    }
    private void setEvent(){
        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        text_bt.setAnimation(bottom_anim);
        imageView2.setAnimation(top_anim);
    }

}
