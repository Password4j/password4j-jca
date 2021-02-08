package org.example;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

import javax.crypto.SecretKeyFactory;

import org.junit.Test;

import com.password4j.jca.providers.Password4jProvider;


public class ProviderTest
{

    @Test
    public void test() throws NoSuchAlgorithmException
    {
        Security.setProperty("crypto.policy", "unlimited");
        Password4jProvider provider4  = new Password4jProvider();
        Security.addProvider(provider4);

        //SecretKeyFactory sk = SecretKeyFactory.getInstance("scrypt",provider4);

        for (Provider provider : Security.getProviders())
        {
            System.out.println(provider.toString());
            for (Provider.Service service : provider.getServices())
            {
                System.out.println("   " + service.getType() + " - " + service.getAlgorithm());
            }


        }

    }
}
