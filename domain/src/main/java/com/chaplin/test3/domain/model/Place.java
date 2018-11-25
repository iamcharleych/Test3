package com.chaplin.test3.domain.model;

public class Place {

    private long mId;
    private long mParentId;
    private String mCode;
    private String mName;
    private String mType;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getParentId() {
        return mParentId;
    }

    public void setParentId(long parentId) {
        mParentId = parentId;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
