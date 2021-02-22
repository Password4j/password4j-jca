package org.example;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

import javax.crypto.SecretKeyFactory;

import com.password4j.jca.spec.ScryptKeySpec;
import org.junit.Test;

import com.password4j.jca.providers.Password4jProvider;


public class ProviderTest
{

    @Test
    public void test() throws NoSuchAlgorithmException
    {
        System.out.println(Security.getProperty("crypto.policy"));
        Password4jProvider provider4  = new Password4jProvider();
        Security.addProvider(provider4);



    }
}
