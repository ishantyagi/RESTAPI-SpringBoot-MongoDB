package com.user.utils;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeUtils {

    private static final Map<String, String> ERROR_CODE_MAP = new HashMap<>();

    public static final String INVALID_REQ_ER = "SF400";

    public static final String CP_ER = "SF001";

    public static final String NPI_ER = "SF002";
    
    public static final String INVALID_INTERNAL_TRANSPORT_CLIENT_ERR = "SF003";  
    
    static {
        ERROR_CODE_MAP.put(INVALID_REQ_ER, "Invalid Req");
        ERROR_CODE_MAP.put(CP_ER, "Received null/error response from Customer Profile Service.");
        ERROR_CODE_MAP.put(INVALID_INTERNAL_TRANSPORT_CLIENT_ERR,"Transport Client hasnt been created nicely internally. "
        		+ "	All the requests to Nile ES would fail");
    }

    public static String getErrorMsg(String errorCode) {
        return ERROR_CODE_MAP.get(errorCode);
    }
}
