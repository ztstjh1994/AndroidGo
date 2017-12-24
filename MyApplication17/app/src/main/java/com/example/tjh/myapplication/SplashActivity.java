package com.example.tjh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv = (ImageView)findViewById(R.id.activity_splash);
        //初始化动画
        initAnim();
    }
    private void initAnim(){
        //透明度动画
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        alpha.setDuration(3000);//动画时长
        alpha.setFillAfter(true);
        //监听动画
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画完成，进入下个界面
                jumpToNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv.startAnimation(alpha);
    }
    private void jumpToNextPage(){
        //判断用户是否是第一次进入应用
        boolean isFirst = PreUtils.getBoolean(this,"isFirst",true);
        Intent intent = new Intent();
        if(isFirst){
            //进入Guide
            intent.setClass(this,GuideActivity.class);
        }else {
            //进入Main
            intent.setClass(this,MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
