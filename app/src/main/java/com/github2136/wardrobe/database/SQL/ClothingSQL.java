package com.github2136.wardrobe.database.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.github2136.sqliteutil.BaseSQLData;
import com.github2136.wardrobe.database.SQLHelper;
import com.github2136.wardrobe.model.entity.ClothingInfo;

import java.util.List;

/**
 * Created by yubin on 2017/8/7.
 */

public class ClothingSQL extends BaseSQLData<ClothingInfo> {
    public ClothingSQL(Context context) {
        super(context);
    }

    @Override
    public SQLiteOpenHelper getSQLHelper(Context context) {
        return new SQLHelper(context);
    }

    public List<ClothingInfo> queryByLimit(int pageNum, int pageSize) {
        return query(null, null, null, null, null, String.format("%d,%d", pageNum * pageSize, pageSize));
    }
}
