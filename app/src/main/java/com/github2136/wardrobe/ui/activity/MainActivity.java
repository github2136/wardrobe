package com.github2136.wardrobe.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.github2136.base.BaseLoadMoreRecyclerAdapter;
import com.github2136.wardrobe.R;
import com.github2136.wardrobe.base.BaseListActivity;
import com.github2136.wardrobe.model.entity.ClothingInfo;
import com.github2136.wardrobe.presenter.MainPresenter;
import com.github2136.wardrobe.ui.adapter.MainAdapter;
import com.github2136.wardrobe.ui.view.IMainView;

import java.util.List;

public class MainActivity extends BaseListActivity<ClothingInfo, MainPresenter> implements IMainView {

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this, this);
    }

    @Override
    protected int getViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListData(Bundle savedInstanceState) {
        mHasItemClick = true;
//        mPresenter.addClothings();
    }

    @Override
    protected BaseLoadMoreRecyclerAdapter<ClothingInfo> getAdapter() {
        return new MainAdapter(mContext, null);
    }

    @Override
    protected void getListData() {
        mPresenter.getClothing(mPageNumber);
    }

    @Override
    public void getClothingSuccessful(List<ClothingInfo> clothingInfos) {
        getDataSuccessful(clothingInfos);
    }

    @Override
    public void getClothingFailure(String msg) {
        getDataFailure();
    }
}
