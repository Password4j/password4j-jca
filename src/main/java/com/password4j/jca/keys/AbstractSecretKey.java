package com.password4j.jca.keys;

import com.password4j.Hash;
import com.password4j.jca.spec.AbstractKeySpec;

import javax.crypto.SecretKey;
import java.util.Arrays;


public abstract class AbstractSecretKey<K extends AbstractKeySpec> implements SecretKey
{
    private final transient Hash hash;

    private char[] password;

    protected AbstractSecretKey(K keySpec, Hash hash)
    {
        this.password = keySpec.getPassword();
        this.hash = hash;
    }

    @Override
    public void destroy()
    {
        if(password != null)
        {
            Arrays.fill(password, Character.MIN_VALUE);
            password = null;
        }
    }

    @Override
    public String getFormat()
    {
        return "RAW";
    }

    @Override
    public byte[] getEncoded()
    {
        return hash.getBytes();
    }

    public Hash getHash()
    {
        return hash;
    }

    public char[] getPassword()
    {
        return password;
    }
}
