package com.github2136.wardrobe.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.github2136.sqliteutil.Column;
import com.github2136.sqliteutil.Table;

import java.util.Date;
import java.util.List;

/**
 * 服装类
 * Created by yb on 2017/7/20.
 */

@Table
public class ClothingInfo implements Parcelable {
    @Column(primaryKey = true)
    private String objectId;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;
    /****************************************/
    @Column
    private String userId;//所属用户
    @Column
    private String ciType;//服装类型
    @Column
    private String ciColor;//服装颜色
    @Column
    private String ciSeason;//季节
    @Column
    private String ciRemark;//备注
    @Column
    private String valid;//是否启用
    @Column
    private Byte viewSeq;//排序
    private List<MediaFile> mediaFiles;//图片

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCiType() {
        return ciType;
    }

    public void setCiType(String ciType) {
        this.ciType = ciType;
    }

    public String getCiColor() {
        return ciColor;
    }

    public void setCiColor(String ciColor) {
        this.ciColor = ciColor;
    }

    public String getCiSeason() {
        return ciSeason;
    }

    public void setCiSeason(String ciSeason) {
        this.ciSeason = ciSeason;
    }

    public String getCiRemark() {
        return ciRemark;
    }

    public void setCiRemark(String ciRemark) {
        this.ciRemark = ciRemark;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Byte getViewSeq() {
        return viewSeq;
    }

    public void setViewSeq(Byte viewSeq) {
        this.viewSeq = viewSeq;
    }

    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }

    public void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.objectId);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
        dest.writeString(this.userId);
        dest.writeString(this.ciType);
        dest.writeString(this.ciColor);
        dest.writeString(this.ciSeason);
        dest.writeString(this.ciRemark);
        dest.writeString(this.valid);
        dest.writeValue(this.viewSeq);
        dest.writeTypedList(this.mediaFiles);
    }

    public ClothingInfo() {}

    protected ClothingInfo(Parcel in) {
        this.objectId = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
        this.userId = in.readString();
        this.ciType = in.readString();
        this.ciColor = in.readString();
        this.ciSeason = in.readString();
        this.ciRemark = in.readString();
        this.valid = in.readString();
        this.viewSeq = (Byte) in.readValue(Byte.class.getClassLoader());
        this.mediaFiles = in.createTypedArrayList(MediaFile.CREATOR);
    }

    public static final Creator<ClothingInfo> CREATOR = new Creator<ClothingInfo>() {
        @Override
        public ClothingInfo createFromParcel(Parcel source) {return new ClothingInfo(source);}

        @Override
        public ClothingInfo[] newArray(int size) {return new ClothingInfo[size];}
    };
}
