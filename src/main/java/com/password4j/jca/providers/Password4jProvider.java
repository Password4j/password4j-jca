package com.password4j.jca.providers;

import java.security.Provider;


public final class Password4jProvider extends Provider
{
    public Password4jProvider()
    {
        super("Password4j", 1.0, "Password4j provider 1.0 implementing bcrypt, scrypt, Argon2");

        // scrypt
        put("SecretKeyFactory.scrypt", "com.password4j.jca.ScryptSpi");
        put("Alg.Alias.SecretKeyFactory.scrypt", "scrypt");
        put("SecretKeyFactory.scrypt ImplementedIn", "Software");
    }
}
