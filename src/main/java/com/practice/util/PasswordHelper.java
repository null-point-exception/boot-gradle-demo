package com.practice.util;

import cn.hutool.core.util.StrUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码加密.
 *
 * @author kexin.ding
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public static void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        PasswordHelper.randomNumberGenerator = randomNumberGenerator;
    }

    public static void setAlgorithmName(String algorithmName) {
        PasswordHelper.algorithmName = algorithmName;
    }

    public static void setHashIterations(int hashIterations) {
        PasswordHelper.hashIterations = hashIterations;
    }

    public static String encryptPassword(String password) {

        String salt = randomNumberGenerator.nextBytes().toHex();
        return encryptPassword(password, salt);
    }

    public static String encryptPassword(String password, String salt) {
        return new SimpleHash(
                //加密算法
                algorithmName,
                //密码
                password,
                //盐值 id + salt
                getCredentialsSalt(password, salt),
                //hash次数
                hashIterations
        ).toHex();
    }

    public static ByteSource getCredentialsSalt(String... salts) {
        return ByteSource.Util.bytes(StrUtil.join(",", salts));
    }

    public static void main(String[] args) {
        String newPassword = encryptPassword("root", NumberUtils.genUUID(5));
        System.out.println(newPassword);
        newPassword = encryptPassword("root");
        System.out.println(newPassword);
    }
}
