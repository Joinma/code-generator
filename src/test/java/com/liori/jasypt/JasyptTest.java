package com.liori.jasypt;

import com.liori.CodeGeneratorApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeGeneratorApplication.class)
public class JasyptTest {

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void test() {
        String originUrl = "jdbc:mysql://localhost:3306/planday?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        String originUserName = "username";
        String originPassword = "password";

        String url = encryptor.encrypt(originUrl);
        String name = encryptor.encrypt(originUserName);
        String password = encryptor.encrypt(originPassword);
        System.out.println("url: " + url);
        System.out.println("userName: " + name);
        System.out.println("password: " + password);
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }
}
