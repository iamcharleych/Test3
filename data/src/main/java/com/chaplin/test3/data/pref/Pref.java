package com.chaplin.test3.data.pref;


import androidx.annotation.NonNull;
import com.chaplin.test3.androidcommon.pref.BooleanPref;
import com.chaplin.test3.androidcommon.pref.FloatPref;
import com.chaplin.test3.androidcommon.pref.IntPref;
import com.chaplin.test3.androidcommon.pref.StringPref;
import com.chaplin.test3.data.model.enitity.CurrencyEntity;

public class Pref {

    public static class Currency {

        public static void init(@NonNull CurrencyEntity currency) {
            CODE.set(currency.getCode());
            SYMBOL.set(currency.getSymbol());
            THOUSANDS_SEPARATOR.set(currency.getThousandsSeparator());
            DECIMAL_SEPARATOR.set(currency.getDecimalSeparator());
            SYMBOL_ON_LEFT.set(currency.isSymbolOnLeft());
            SPACE_BETWEEN_AMOUNT_AND_SYMBOL.set(currency.isSpaceBetweenAmountAndSymbol());
            ROUNDING_COEFFICIENT.set(currency.getRoundingCoefficient());
            DECIMAL_DIGITS.set(currency.getDecimalDigits());
        }

        public static final StringPref CODE = new StringPref("currency_code", "US");
        public static final StringPref SYMBOL = new StringPref("currency_symbol", "$");
        public static final StringPref THOUSANDS_SEPARATOR = new StringPref("currency_thousand_separator", ",");
        public static final StringPref DECIMAL_SEPARATOR = new StringPref("currency_decimal_separator", ".");
        public static final BooleanPref SYMBOL_ON_LEFT = new BooleanPref("currency_symbol_on_left", true);
        public static final BooleanPref SPACE_BETWEEN_AMOUNT_AND_SYMBOL =
                new BooleanPref("currency_space_between_amnt_and_symbol", false);
        public static final FloatPref ROUNDING_COEFFICIENT = new FloatPref("currency_rounding_coef", 0f);
        public static final IntPref DECIMAL_DIGITS = new IntPref("currency_decimal_digits", 2);

    }

    public static class Session {
        public static final StringPref BASE_POLLING_URL = new StringPref("base_polling_url", "");
    }
}
