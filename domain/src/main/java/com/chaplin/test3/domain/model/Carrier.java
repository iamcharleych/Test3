package com.chaplin.test3.domain.model;

public class Carrier {

    private long mId;
    private String mCode;
    private String mDisplayCode;
    private String mName;
    private String mImageUrl;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getDisplayCode() {
        return mDisplayCode;
    }

    public void setDisplayCode(String displayCode) {
        mDisplayCode = displayCode;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
