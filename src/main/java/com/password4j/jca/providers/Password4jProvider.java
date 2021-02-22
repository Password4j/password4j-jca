package com.password4j.jca.providers;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;


public final class Password4jProvider extends Provider
{
    public Password4jProvider()
    {
        super("Password4j", 1.0, "Password4j provider 1.0 implementing bcrypt, scrypt, Argon2");

        AccessController.doPrivileged(new PrivilegedAction<Object>()
        {
            @Override
            public Object run()
            {
                // Argon2
                put("SecretKeyFactory.argon2", "com.password4j.jca.spi.Argon2Spi");
                put("Alg.Alias.SecretKeyFactory.argon2", "argon2");
                put("SecretKeyFactory.argon2 ImplementedIn", "Software");

                // scrypt
                put("SecretKeyFactory.scrypt", "com.password4j.jca.spi.ScryptSpi");
                put("Alg.Alias.SecretKeyFactory.scrypt", "scrypt");
                put("SecretKeyFactory.scrypt ImplementedIn", "Software");
                

                // bcrypt
                put("SecretKeyFactory.bcrypt", "com.password4j.jca.spi.BcryptSpi");
                put("Alg.Alias.SecretKeyFactory.bcrypt", "bcrypt");
                put("SecretKeyFactory.bcrypt ImplementedIn", "Software");
                return null;
            }
        });

    }
}
