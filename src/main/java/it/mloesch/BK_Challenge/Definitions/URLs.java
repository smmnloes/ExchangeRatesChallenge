package it.mloesch.BK_Challenge.Definitions;

public class URLs {

    /**
     * Internal API URLS
     **/
    public static final String INTERNAL_ROOT_URL = "/api/exchange-rate";
    public static final String INTERNAL_RATE_INFO_URL = INTERNAL_ROOT_URL + "/{date}/{baseCurrency}/{targetCurrency}";
    public static final String INTERNAL_RATE_HISTORY_ROOT_URL = INTERNAL_ROOT_URL + "/history";
    public static final String INTERNAL_RATE_HISTORY_DAILY_URL = INTERNAL_RATE_HISTORY_ROOT_URL + "/daily/{year}/{month}/{day}";
    public static final String INTERNAL_RATE_HISTORY_MONTHLY_URL = INTERNAL_RATE_HISTORY_ROOT_URL + "/monthly/{year}/{month}";



    /**
     * External API URLS
     **/
    public static final String EXTERNAL_BASE_URL = "https://api.exchangeratesapi.io";
    public static final String EXTERNAL_HISTORY_URL = EXTERNAL_BASE_URL + "/history";


}
