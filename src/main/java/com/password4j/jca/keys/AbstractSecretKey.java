package com.password4j.jca.keys;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.SecretKey;

import com.password4j.Hash;
import com.password4j.jca.spec.AbstractKeySpec;


public abstract class AbstractSecretKey<K extends AbstractKeySpec> implements SecretKey
{
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    private final Hash hash;

    private char[] password;

    public AbstractSecretKey(K keySpec, Hash hash)
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
        return hash.getResult().getBytes(ENCODING);
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
