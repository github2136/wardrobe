package com.github2136.wardrobe.ui.adapter;

import android.content.Context;

import com.github2136.base.BaseLoadMoreRecyclerAdapter;
import com.github2136.base.ViewHolderRecyclerView;
import com.github2136.wardrobe.R;
import com.github2136.wardrobe.model.entity.ClothingInfo;
import com.github2136.wardrobe.util.Constant;

import java.util.List;

/**
 * Created by yubin on 2017/7/18.
 */

public class MainAdapter extends BaseLoadMoreRecyclerAdapter<ClothingInfo> {

    public MainAdapter(Context context, List<ClothingInfo> list) {
        super(context, list);
    }


    @Override
    protected int getPageSize() {
        return Constant.PAGE_SIZE;
    }

    @Override
    public int getLayoutId(int i) {
        return R.layout.item_main;
    }

    @Override
    protected void onBindView(ClothingInfo clothingInfo, ViewHolderRecyclerView viewHolderRecyclerView, int i) {

    }
}