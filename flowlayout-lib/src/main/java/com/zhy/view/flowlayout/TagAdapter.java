package com.zhy.view.flowlayout;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class TagAdapter<T> {

    private List<T> mTagData;
    private OnDataChangedListener mOnDataChangedListener;
    private HashSet<Integer> mCheckedPosList = new HashSet<>();

    public TagAdapter() {
        mTagData = new ArrayList<>();
    }

    public TagAdapter(List<T> data) {
        mTagData = data;
    }

    public TagAdapter(T[] data) {
        mTagData = new ArrayList<>(Arrays.asList(data));
    }

    public interface OnDataChangedListener {
        void onChanged();
    }

    void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }

    public void setSelectedList(int... pos) {
        for (int po : pos) mCheckedPosList.add(po);
        notifyDataChanged();
    }

    /**
     *
     * 添加 List 参数的 setSelectedList 方法，方便构造预选数据
     */
    public void setSelectedList(List<Integer> pos) {
        mCheckedPosList.addAll(pos);
        notifyDataChanged();
    }

    HashSet<Integer> getPreCheckedList() {
        return mCheckedPosList;
    }


    public int getCount() {
        return mTagData == null ? 0 : mTagData.size();
    }

    public void notifyDataChanged() {
        mOnDataChangedListener.onChanged();
    }

    public T getItem(int position) {
        return mTagData.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);

    /**
     * 获得适配器中数据
     */
    public List<T> getTagData() {
        return mTagData;
    }

    /**
     * 更新适配器中数据
     */
    public void updateData(List<T> data) {
        mTagData.clear();
        mTagData.addAll(data);
        notifyDataChanged();
    }
}