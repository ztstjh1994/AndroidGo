package com.example.tjh.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tjh.myapplication.fragment.CaijingFragment;
import com.example.tjh.myapplication.fragment.JunshiFragment;
import com.example.tjh.myapplication.fragment.KejiFragment;
import com.example.tjh.myapplication.fragment.ShehuiFragment;
import com.example.tjh.myapplication.fragment.TiyuFragment;
import com.example.tjh.myapplication.fragment.ToutiaoFragment;
import com.example.tjh.myapplication.fragment.YuleFragment;

/**
 * Created by TJH on 2017/12/2.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"头条","社会","娱乐","体育","军事","科技","财经"};
    public MainPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position){
        if(position == 0){
            return new ToutiaoFragment();
        }else if(position == 1){
            return new ShehuiFragment();
        }else if(position == 2){
            return new YuleFragment();
        }else if(position == 3){
            return new TiyuFragment();
        }else if(position == 4){
            return new JunshiFragment();
        }else if(position == 5){
            return new KejiFragment();
        }else if(position == 6){
            return new CaijingFragment();
        }else return null;
    }
    @Override
    public int getCount(){
        return mTitles.length;
    }
    @Override
    public CharSequence getPageTitle(int position){
        return mTitles[position];
    }
}
