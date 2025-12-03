package br.gel.casa.consultarfb.infraestructure.utils;

import java.util.Base64;

public class EncodeUtil {

    private EncodeUtil() {}   

    public static String encodeBasic(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

}
