package com.chaplin.test3.data.model.enitity;

import com.google.gson.annotations.SerializedName;

public class CurrencyEntity {

    @SerializedName("Code")
    private String mCode;
    @SerializedName("Symbol")
    private String mSymbol;
    @SerializedName("ThousandsSeparator")
    private String mThousandsSeparator;
    @SerializedName("DecimalSeparator")
    private String mDecimalSeparator;
    @SerializedName("SymbolOnLeft")
    private boolean mSymbolOnLeft;
    @SerializedName("SpaceBetweenAmountAndSymbol")
    private boolean mSpaceBetweenAmountAndSymbol;
    @SerializedName("RoundingCoefficient")
    private float mRoundingCoefficient;
    @SerializedName("DecimalDigits")
    private int mDecimalDigits;

    public String getCode() {
        return mCode;
    }

    public void setCode(String mCode) {
        this.mCode = mCode;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String mSymbol) {
        this.mSymbol = mSymbol;
    }

    public String getThousandsSeparator() {
        return mThousandsSeparator;
    }

    public void setThousandsSeparator(String mThousandsSeparator) {
        this.mThousandsSeparator = mThousandsSeparator;
    }

    public String getDecimalSeparator() {
        return mDecimalSeparator;
    }

    public void setDecimalSeparator(String mDecimalSeparator) {
        this.mDecimalSeparator = mDecimalSeparator;
    }

    public boolean isSymbolOnLeft() {
        return mSymbolOnLeft;
    }

    public void setSymbolOnLeft(boolean mSymbolOnLeft) {
        this.mSymbolOnLeft = mSymbolOnLeft;
    }

    public boolean isSpaceBetweenAmountAndSymbol() {
        return mSpaceBetweenAmountAndSymbol;
    }

    public void setSpaceBetweenAmountAndSymbol(boolean mSpaceBetweenAmountAndSymbol) {
        this.mSpaceBetweenAmountAndSymbol = mSpaceBetweenAmountAndSymbol;
    }

    public float getRoundingCoefficient() {
        return mRoundingCoefficient;
    }

    public void setRoundingCoefficient(float mRoundingCoefficient) {
        this.mRoundingCoefficient = mRoundingCoefficient;
    }

    public int getDecimalDigits() {
        return mDecimalDigits;
    }

    public void setDecimalDigits(int mDecimalDigits) {
        this.mDecimalDigits = mDecimalDigits;
    }
}
