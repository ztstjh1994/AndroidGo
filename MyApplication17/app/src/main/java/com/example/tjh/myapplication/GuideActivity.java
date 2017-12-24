package com.example.tjh.myapplication;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button btnStart;
    private int[] imageIds = new int[]{R.drawable.guide_0,R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        btnStart = (Button)findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreUtils.putBoolean(GuideActivity.this,"isFirst",false);
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        initDate();
    }
    private void initDate(){
        imageViews = new ArrayList<ImageView>();
        for(int i = 0;i<imageIds.length;i++){
            ImageView image = new ImageView(this);
            image.setBackgroundResource(imageIds[i]);
            imageViews.add(image);
        }
        GuideAdapter adapter = new GuideAdapter();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                if(position == imageIds.length-1){
                    btnStart.setVisibility(View.VISIBLE);
                }else {
                    btnStart.setVisibility(View.GONE);
                }
            }
        });
    }
    class GuideAdapter extends PagerAdapter{
        @Override
        public int getCount(){
            return  imageViews.size();
        }
        @Override
        public boolean isViewFromObject(View arg0,Object arg1){
            return arg0 == arg1;
        }
        @Override
        public Object instantiateItem(ViewGroup container,int position){
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }
        @Override
        public void destroyItem(ViewGroup container,int position,Object object){
            container.removeView((View) object);
        }
    }
}
