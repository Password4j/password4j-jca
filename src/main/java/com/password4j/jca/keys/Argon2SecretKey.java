package com.password4j.jca.keys;

import com.password4j.Hash;
import com.password4j.jca.spec.Argon2KeySpec;


public class Argon2SecretKey extends AbstractSecretKey<Argon2KeySpec>
{
    public Argon2SecretKey(Argon2KeySpec argon2KeySpec, Hash hash)
    {
        super(argon2KeySpec, hash);
    }

    @Override
    public String getAlgorithm()
    {
        return "argon2";
    }
}
