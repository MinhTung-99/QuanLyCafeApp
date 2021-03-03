package com.quanlyquancafeapp.utils;

public class PriceUtil {
    public static String setupPrice(String takePrice){
        int count = 0;
        String setupPriceStr = "";
        String str = takePrice;
        for(int i = takePrice.length()-3; i >= 0; i--){
            setupPriceStr += str.charAt(i);
            count++;
            if(count == 3){
                setupPriceStr += ",";
                count = 0;
            }
        }
        String s = new StringBuilder(setupPriceStr).reverse().toString();
        String priceStr = "";
        if(s.charAt(0) == ',') {
            for(int i = 1; i < s.length(); i++) {
                priceStr += s.charAt(i);
            }
        }else {
            return s;
        }
        return priceStr;
    }
    public static String getPriceByComma(String takePrice){
        String setupPrice="";
        for(int i = 0; i < takePrice.length(); i++){
            if(takePrice.charAt(i) == ','){
                break;
            }
            setupPrice += takePrice.charAt(i);
        }
        return setupPrice;
    }
}
