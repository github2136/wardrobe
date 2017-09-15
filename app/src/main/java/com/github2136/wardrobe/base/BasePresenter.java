package com.github2136.wardrobe.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.github2136.base.mvp.BaseMVPPresenter;
import com.github2136.base.mvp.IBaseMVPView;
import com.github2136.wardrobe.model.util.Response;


/**
 * presenter基础类
 */
public abstract class BasePresenter<V extends IBaseMVPView> extends BaseMVPPresenter<V> {

    public BasePresenter(AppCompatActivity activity, V view) {
        super(activity, view);
    }

    public BasePresenter(Fragment fragment, V view) {
        super(fragment, view);
    }

    protected boolean isSuccess(Response response) {
        return response != null && response.getRequestCode() == Response.CODE_SUCCESSFUL;
    }

    protected String getFailedStr(Response response) {
        if (response == null) {
            return failedStr;
        } else {
            return response.getMsg();
        }
    }
}
