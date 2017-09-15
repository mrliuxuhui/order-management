package com.flyingwillow.restaurant.shiro.util;

import com.flyingwillow.restaurant.util.Config;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by liuxuhui on 2017/9/14.
 */
public class PasswordHelper {

    public static String cryptPassword(String password,String salt, String username) {
        String algorithmName = Config.gets("shiro.encrypt.algorithm","MD5");
        Integer iterations = Config.getInt("shiro.encrypt.iterations",1);
        salt = StringUtils.isBlank(salt)?username:salt;
        return new SimpleHash(algorithmName,password, ByteSource.Util.bytes(salt),iterations).toHex();
    }

    public static String randomSalt(){
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
        String salt= secureRandomNumberGenerator.nextBytes().toHex();
        return salt;
    }
}
