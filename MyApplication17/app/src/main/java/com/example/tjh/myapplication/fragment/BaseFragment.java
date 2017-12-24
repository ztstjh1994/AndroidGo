package com.example.tjh.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tjh.myapplication.R;
import com.example.tjh.myapplication.RefreshLayout;
import com.example.tjh.myapplication.WebViewActivity;
import com.example.tjh.myapplication.adapter.NewsListAdapter;
import com.example.tjh.myapplication.domain.NewsBean;
import com.example.tjh.myapplication.engin.JNI;
import com.example.tjh.myapplication.engin.NetWork;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TJH on 2017/12/10.
 */

public abstract class BaseFragment extends Fragment {
    RefreshLayout refreshLayout;
    ListView listView;
    ImageView ivError;
    ProgressBar progressBar;
    NewsListAdapter adapter;
    List<NewsBean.ResultBean.DataBean> datas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_toutiao,container,false);
        refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        listView = (ListView)view.findViewById(R.id.list_view);
        ivError = (ImageView)view.findViewById(R.id.iv_error);
        progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        initView();
        initData();
        return view;
    }
    private void initData(){
        progressBar.setVisibility(View.VISIBLE);
        ivError.setVisibility(View.GONE);
        refesh();
    }
    private void initView(){
        //为ListView设置适配器，从而显示到界面上
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refesh();
            }
        });
        refreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                refesh();
            }
        });

        refreshLayout.setHasMore(false);     //没有更多数据
        /*加载失败，点击重新加载*/
        ivError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        /*ListView条目点击事件*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsBean.ResultBean.DataBean dataBean = datas.get(position);
                Intent intent = WebViewActivity.newIntent(getContext(),dataBean.getUrl());
                getActivity().startActivity(intent);
            }
        });
    }
    private void refesh(){
        NetWork.createApi().getNews(JNI.getAppKey(),getType())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(NewsBean newsBean) throws Exception {
                        refreshLayout.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        refreshLayout.setRefreshing(false);
                        datas = newsBean.getResult().getData();
                        adapter = new NewsListAdapter(datas, getContext());
                        listView.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ivError.setVisibility(View.VISIBLE);
                        refreshLayout.setEnabled(false);
                        progressBar.setVisibility(View.GONE);
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"请求失败",Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();
                    }
                });
    }
    protected abstract String getType();
}
