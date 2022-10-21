package com.password4j.jca.providers;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.Security;


/**
 *
 *
 * @author David Bertoldi
 * @since 0.1.0
 */
public final class Password4jProvider extends Provider
{

    private static final String[] SUPPORTED_ALGS = {"argon2", "scrypt", "bcrypt"};

    public static void enable()
    {
        Password4jProvider instance  = new Password4jProvider();
        Security.addProvider(instance);
    }

    public static void enableUnlimited()
    {
        Security.setProperty("crypto.policy", "unlimited");
        enable();
    }


    public Password4jProvider()
    {
        super("Password4j", "1.0", "Password4j provider 1.0 implementing bcrypt, scrypt, Argon2");

        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            for(String alg : SUPPORTED_ALGS)
            {
                put("SecretKeyFactory." + alg, "com.password4j.jca.spi." + (alg.substring(0, 1).toUpperCase() + alg.substring(1)) + "Spi");
                put("Alg.Alias.SecretKeyFactory." + alg, alg);
                put("SecretKeyFactory." + alg + " ImplementedIn", "Software");
            }
            return null;
        });

    }



}
