package com.flyingwillow.restaurant.shiro.util;

import com.alibaba.fastjson.JSON;
import com.flyingwillow.restaurant.util.serializer.KryoSerializeUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by liuxuhui on 2017/9/15.
 */
public class PasswordHelperTest {

    @Test
    public void testCryptPassword() throws Exception {

        //String salt = PasswordHelper.randomSalt();
        String salt = "63ba6bb5e19948362b0e8389734a1b7e";
        System.out.println("salt:"+salt);

        System.out.println();

        String encrypt = PasswordHelper.cryptPassword("flyingwillow",salt,"liuxuhui");

        System.out.println(encrypt);

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("MD5");

        matcher.setHashIterations(1);
        matcher.setStoredCredentialsHexEncoded(true);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("liuxuhui","flyingwillow");
        AuthenticationInfo info = new SimpleAuthenticationInfo("liuxuhui",encrypt.toCharArray(),
                ByteSource.Util.bytes(salt),"");
        System.out.println(matcher.doCredentialsMatch(usernamePasswordToken,info));
    }

    @Test
    public void testSession(){

        SimpleSession session = new SimpleSession("127.0.0.1");
        JavaUuidSessionIdGenerator generator = new JavaUuidSessionIdGenerator();
        session.setId(generator.generateId(session));

        System.out.println(JSON.toJSONString(session));

        byte[] bSession = KryoSerializeUtil.serialize(session);
        System.out.println(JSON.toJSONString(bSession));

        SimpleSession deSession = KryoSerializeUtil.deserialize(bSession);
        System.out.println(JSON.toJSONString(deSession));
    }
}