package com.password4j.jca.providers;

import org.apache.commons.lang3.StringUtils;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;


public final class Password4jProvider extends Provider
{

    private static final String[] SUPPORTED_ALGS = {"argon2", "scrypt", "bcrypt"};


    public Password4jProvider()
    {
        super("Password4j", "1.0", "Password4j provider 1.0 implementing bcrypt, scrypt, Argon2");

        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            for(String alg : SUPPORTED_ALGS)
            {
                put("SecretKeyFactory." + alg, "com.password4j.jca.spi." + StringUtils.capitalize(alg) + "Spi");
                put("Alg.Alias.SecretKeyFactory." + alg, alg);
                put("SecretKeyFactory." + alg + " ImplementedIn", "Software");
            }
            return null;
        });

    }
}
