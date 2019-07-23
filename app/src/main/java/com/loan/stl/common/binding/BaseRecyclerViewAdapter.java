package com.loan.stl.common.binding;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.loan.stl.BR;
import com.loan.stl.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by russell on 2019/3/8
 * email:igruwestbrook@gmail.com
 * Description:
 */
public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder>  {

  @SuppressLint("UseSparseArrays")
  private Map<Integer,ItemClick> mListenerMap= new HashMap<>();

    public List<T> getTList() {
        return mTList;
    }

    private List<T> mTList;
  private int variableId;
  private int layoutId;
  private ItemClick mItemClick;
  private PositionClick positionClick;
  private boolean hasLast;
  private final int NORMAL_ITEMS=0;
  private final int LAST_ITEM=1;
  private boolean showTip=false;
  private String tip="";
  private View footerView;
    public BaseRecyclerViewAdapter(List<T> TList, int variableId, int layoutId) {
       this(TList,variableId,layoutId,false);
    }
    public void addData(List<T> list){
        mTList.addAll(list);
        notifyDataSetChanged();
    }


    public BaseRecyclerViewAdapter(List<T> TList, int variableId, int layoutId, boolean hasLast) {
        mTList = TList;
        this.variableId = variableId;
        this.layoutId = layoutId;
        this.hasLast=hasLast;
    }
    /*
    设置数据
     */
    public void setTList(List<T> TList) {
        mTList = TList;
        notifyDataSetChanged();
    }
    /*
    设置单个item内的点击事件
     */
    public void setItemClick(int id,ItemClick listener){
        mListenerMap.put(id,listener);
        notifyDataSetChanged();
    }
    /*
    设置item的点击事件
     */
    public void setItemClick(ItemClick itemClick) {
        mItemClick = itemClick;
    }
    @NonNull
    @Override
    public BaseRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        BaseRecyclerViewAdapter.ViewHolder viewHolder=null;
        if(i==NORMAL_ITEMS){
            ViewDataBinding viewDataBinding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),layoutId,viewGroup,false);
            View view=  viewDataBinding.getRoot();
            viewHolder=new BaseRecyclerViewAdapter.ViewHolder(view);
            viewHolder.setViewDataBinding(viewDataBinding);
        }else if(i==LAST_ITEM) {
            footerView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_last_one,viewGroup,false);
            if(showTip){
                TextView textView=footerView.findViewById(R.id.text);
                textView.setText(tip);
                textView.setVisibility(View.VISIBLE);
            }
            viewHolder=new ViewHolder(footerView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        if(i>=mTList.size()){
            return ;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mItemClick!=null)
                   mItemClick.Click(v,mTList.get(i));
               if(positionClick!=null)
                   positionClick.click(v,i);
            }
        });
        if(mListenerMap.size()>0){
            for (final Integer id : mListenerMap.keySet()) {
                View item=viewHolder.itemView.findViewById(id);
                if(item!=null)
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListenerMap.get(id).Click(v,mTList.get(i));
                        }
                    });
            }
        }
        viewHolder.setContent(mTList.get(i),i);
    }

    @Override
    public int getItemViewType(int position) {
        if(position>=mTList.size()){
            return  LAST_ITEM;
        }
        return NORMAL_ITEMS;
    }

    @Override
    public int getItemCount() {
        int size=0;
        if(mTList!=null && mTList.size()>0){
            size=mTList.size();
        }
        if(hasLast && size>0){
            size++;
        }
        return size;
    }
    public void lodMore(boolean flag){
        if(hasLast){
            showTip=true;
            if(flag)
                tip="正在加载更多...";
            else
                tip="没有更多了";
            if(footerView!=null){
                TextView textView=footerView.findViewById(R.id.text);
                textView.setText(tip);
            }

        }
    }

    public void setLast(boolean b) {
        this.hasLast=b;
        notifyDataSetChanged();
    }

    public void setPositionClick(PositionClick positionClick) {
        this.positionClick = positionClick;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding mViewDataBinding;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        void setViewDataBinding(ViewDataBinding viewDataBinding) {
            mViewDataBinding = viewDataBinding;
        }
       void setContent(T t,int position){
            mViewDataBinding.setVariable(variableId,t);
            mViewDataBinding.setVariable(BR.position,position);
            mViewDataBinding.executePendingBindings();
        }
    }
}

