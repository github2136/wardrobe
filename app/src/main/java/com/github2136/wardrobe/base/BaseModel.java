package com.github2136.wardrobe.base;

import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

import com.github2136.base.mvp.BaseMVPModel;
import com.github2136.util.SPUtil;


/**
 * model基础类
 */
public abstract class BaseModel  extends BaseMVPModel {

    public BaseModel(AppCompatActivity activity) {
        super(activity);
    }

    public BaseModel(Fragment fragment) {
        super(fragment);
    }
}