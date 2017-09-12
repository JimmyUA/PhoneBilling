package com.sergey.prykhodko.managers;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {

    public static String encodePassword(String password){
        return DigestUtils.sha512Hex(password);
    }

}
