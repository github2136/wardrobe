package com.github2136.wardrobe.model.entity;

import com.github2136.sqliteutil.Column;
import com.github2136.sqliteutil.Table;

import java.util.Date;

/**
 * 图片文件
 * Created by yubin on 2017/8/17.
 */
@Table
public class MediaFile {
    @Column
    private String fmId;//文件主键
    @Column
    private String fmPath;//文件路径
    @Column
    private String ciId;//所属服装
    @Column
    private String valid;//是否启用
    @Column
    private Date modifyDate;//修改时间
    @Column
    private String modifyer;//修改人
    @Column
    private Byte viewSeq;//排序

    public String getFmId() {
        return fmId == null ? "" : fmId;
    }

    public void setFmId(String fmId) {
        this.fmId = fmId;
    }

    public String getFmPath() {
        return fmPath == null ? "" : fmPath;
    }

    public void setFmPath(String fmPath) {
        this.fmPath = fmPath;
    }

    public String getCiId() {
        return ciId == null ? "" : ciId;
    }

    public void setCiId(String ciId) {
        this.ciId = ciId;
    }

    public String getValid() {
        return valid == null ? "" : valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyer() {
        return modifyer == null ? "" : modifyer;
    }

    public void setModifyer(String modifyer) {
        this.modifyer = modifyer;
    }

    public Byte getViewSeq() {
        return viewSeq;
    }

    public void setViewSeq(Byte viewSeq) {
        this.viewSeq = viewSeq;
    }

}
