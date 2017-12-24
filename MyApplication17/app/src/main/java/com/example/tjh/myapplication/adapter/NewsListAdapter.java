package com.example.tjh.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tjh.myapplication.R;
import com.example.tjh.myapplication.domain.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by TJH on 2017/12/5.
 */

public class NewsListAdapter extends BaseAdapter {
    private List<NewsBean.ResultBean.DataBean> data;
    private Context context;
    private LayoutInflater layoutInflater;
    public NewsListAdapter(List<NewsBean.ResultBean.DataBean> data,Context context){
        this.data = data;
        this.context = context;
    }
    public void setData(List<NewsBean.ResultBean.DataBean> data){
        this.data = data;
    }
    @Override
    public int getCount(){
        return data.size();
    }
    @Override
    public Object getItem(int position){
        return position;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;
        ViewHolder holder = null;
        if(convertView == null){
            view = View.inflate(context, R.layout.item_news,null);
            holder = new ViewHolder();
            /*convertView = layoutInflater.inflate(R.layout.item_news,null);*/
            holder.ivIcon = (ImageView)view.findViewById(R.id.iv_icon);
            holder.tvTitle = (TextView)view.findViewById(R.id.tv_title);
            holder.tvFrom = (TextView)view.findViewById(R.id.tv_from);
            holder.tvDate = (TextView)view.findViewById(R.id.tv_date);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }
        //数据填充
        NewsBean.ResultBean.DataBean dataBean = data.get(position);
        holder.tvTitle.setText(dataBean.getTitle());
        holder.tvFrom.setText(dataBean.getAuthor_name());
        holder.tvDate.setText(dataBean.getDate());
        if(!TextUtils.isEmpty(dataBean.getThumbnail_pic_s())){
            Picasso.with(context)
                    .load(dataBean.getThumbnail_pic_s())
                    .placeholder(R.drawable.iv_icon)
                    .error(R.drawable.iv_icon)
                    .into(holder.ivIcon);
        }
        return view;
    }
    public static class ViewHolder{
        ImageView ivIcon;
        TextView tvTitle,tvFrom,tvDate;
    }
}
