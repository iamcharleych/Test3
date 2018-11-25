package com.chaplin.test3.core.utils;

import com.chaplin.test3.data.pref.Pref;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Simple price formatter. Used ONLY for demo purpose. <br/>
 * TODO: provide proper implementation which use all {@link com.chaplin.test3.data.model.enitity.CurrencyEntity} capabilities
 */
public class PriceFormatter {

    private final NumberFormat mNumberFormatter;

    public PriceFormatter() {
        mNumberFormatter = NumberFormat.getNumberInstance();
        mNumberFormatter.setMaximumFractionDigits(Pref.Currency.DECIMAL_DIGITS.get());
        mNumberFormatter.setMinimumFractionDigits(Pref.Currency.DECIMAL_DIGITS.get());
        mNumberFormatter.setRoundingMode(RoundingMode.HALF_UP);
    }

    public String format(float price) {
        StringBuilder sb = new StringBuilder(mNumberFormatter.format(price));

        if (Pref.Currency.SYMBOL_ON_LEFT.get()) {
            if (Pref.Currency.SPACE_BETWEEN_AMOUNT_AND_SYMBOL.get()) {
                sb.insert(0, " ");
            }
            sb.insert(0, Pref.Currency.SYMBOL.get());
        } else {
            if (Pref.Currency.SPACE_BETWEEN_AMOUNT_AND_SYMBOL.get()) {
                sb.append(" ");
            }
            sb.append(Pref.Currency.SYMBOL.get());
        }

        return sb.toString();
    }
}
