package com.liori.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeneratorUtil {

    private static String APP_ID;

    private static String SECRET;

    @Value("${test.key}")
    public void setCCBIPADDRESS(String appId) {
        APP_ID = appId;
    }

    public static void testGetKey() {
        System.out.println("key: " + APP_ID);
    }


}
