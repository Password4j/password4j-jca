package com.password4j.jca.spec;

import java.security.spec.KeySpec;


public abstract class AbstractKeySpec implements KeySpec
{

    private final char[] password;

    private final byte[] salt;

    public AbstractKeySpec(char[] password, byte[] salt)
    {
        this.password = password;
        this.salt = salt;
    }

    public char[] getPassword()
    {
        return password;
    }

    public byte[] getSalt()
    {
        return salt;
    }
}
