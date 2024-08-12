package io.aanagtalon.backend.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String key) {
        return "Hello " + name + ",\n\n Your new account has been created! Please click on the link below to verify your account.\n\n" +
                getVerificationUrl(host, key) + "\n\nPeachyCode Support Team";
    }

    public static String getVerificationUrl(String host, String key) {
        return host + "/verify/account?key=" + key;
    }
}
