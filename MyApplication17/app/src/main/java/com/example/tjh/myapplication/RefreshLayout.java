package com.example.tjh.myapplication;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by TJH on 2017/12/2.
 */


public class RefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {
    private ListView mListView;     //ListView实例
    private OnLoadListener mOnLoadListener;     //上拉监听器，到了最底部的上拉加载操作
    private View mListViewFooter;     //ListView的加载中footer
    private boolean isLoading = false;
    private boolean hasMore = true;
    private int mTouchSlop;     //滑动到最下面时候的上拉操作
    private int mYDown;     //按下时的Y坐标
    private int mLastY;     //抬起时的Y坐标

    public RefreshLayout(Context context){
        this(context,null);
    }
    public RefreshLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer,null,false);
    }
    @Override
    protected void onLayout(boolean changed,int left,int top,int right,int bottom){
        super.onLayout(changed,left,top,right,bottom);
        if(mListView == null){
            getListView();
        }
    }
    private void getListView(){
        int childs = getChildCount();
        if(childs>0){
            View childView = getChildAt(0);
            if(childView instanceof ListView){
                mListView = (ListView)childView;
                mListView.setOnScrollListener(this);
                //Log.d(VIEW_LOG_TAG,"### 找到listview");
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        final int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                //按下
                mYDown = (int)event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                //移动
                mLastY = (int)event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                //抬起
                if(canLoad()){
                    loadData();
                }
                break;
                default:
                    break;
        }
        return super.dispatchTouchEvent(event);
    }
    /*判断是否可以加载更多*/
    private boolean canLoad(){
        return isBottom() && !isLoading && isPullUp();
    }
    /*判断是否到了最底部*/
    private boolean isBottom(){
        if(mListView != null && mListView.getAdapter() != null){
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount()-1);
        }
        return false;
    }
    /*判断是否为上拉操作*/
    private boolean isPullUp(){
        return (mYDown-mLastY)>=mTouchSlop;
    }
    /*如果到了最底部，而且是上拉操作*/
    private void loadData(){
        if(mOnLoadListener != null){
            //设置状态
            setLoading(true);
            mOnLoadListener.onLoad();
        }
    }

    public void setLoading(boolean loading){
        isLoading = loading;
        if(isLoading){
            mListView.addFooterView(mListViewFooter);
        }else {
            mListView.removeFooterView(mListViewFooter);
            mYDown = 0;
            mLastY = 0;
        }
    }
    public void setOnLoadListener(OnLoadListener loadListener){
        mOnLoadListener = loadListener;
    }
    @Override
    public void onScrollStateChanged(AbsListView view,int scrollState){

    }
    @Override
    public void onScroll(AbsListView view,int firstVisibleItem,int visibleItemCount,int totalItemCount) {
        if (hasMore && mListView.getLastVisiblePosition() == mListView.getAdapter().getCount()-1 && isLoading == false) {
            setLoading(true);
            mOnLoadListener.onLoad();
        }else {
            Toast.makeText(getContext(),"No more",Toast.LENGTH_SHORT);
        }
    }
        /*else {
            Toast.makeText(getContext(),"No more",Toast.LENGTH_SHORT).show();
        }*/

    public interface OnLoadListener{
        public void onLoad();
    }
    public void setHasMore(boolean hasMore){
        this.hasMore = hasMore;
    }
}

