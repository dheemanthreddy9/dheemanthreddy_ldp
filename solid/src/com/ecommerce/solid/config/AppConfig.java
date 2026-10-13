package com.ecommerce.solid.config;

public final class AppConfig {

    private AppConfig() {
    }

    public static final String DEFAULT_CURRENCY = "INR";
    public static final String CURRENCY_SYMBOL = "₹";

    public static final double GROCERY_GST_RATE = 0.05;
    public static final double ELECTRONICS_GST_RATE = 0.18;
    public static final double DEFAULT_GST_RATE = 0.18;

    public static final double MINIMUM_ORDER_AMOUNT = 1.00;
    public static final double FREE_DELIVERY_THRESHOLD = 500.00;
    public static final double STANDARD_DELIVERY_FEE = 40.00;

    public static final String PAYMENT_METHOD_UPI = "UPI";
    public static final String PAYMENT_METHOD_NET_BANKING = "NET_BANKING";

    public static final String DEFAULT_SMS_SENDER_ID = "QUICKCART";
    public static final String COUNTRY_CODE_INDIA = "+91-";

    public static final String CATEGORY_GROCERY = "GROCERY";
    public static final String CATEGORY_ELECTRONICS = "ELECTRONICS";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
