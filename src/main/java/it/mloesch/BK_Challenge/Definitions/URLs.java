package it.mloesch.BK_Challenge.Definitions;

public class URLs {
    public static final String EXCHANGE_RATE_ROOT_URL = "/api/exchange-rate";
    public static final String EXCHANGE_INFO_URL = EXCHANGE_RATE_ROOT_URL + "/{date}/{baseCurrency}/{targetCurrency}";

    public static final String EXCHANGE_RATES_API_BASE_URL = "https://api.exchangeratesapi.io";

    public static final String EXCHANGE_RATES_API_LATEST_URL = EXCHANGE_RATES_API_BASE_URL + "/latest";
    public static final String EXCHANGE_RATES_API_HISTORY_URL = EXCHANGE_RATES_API_BASE_URL + "/history";



}
