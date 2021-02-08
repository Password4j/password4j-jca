package com.password4j.jca.keys;

import com.password4j.Hash;
import com.password4j.jca.spec.BcryptKeySpec;


public class BcryptSecretKey extends AbstractSecretKey<BcryptKeySpec>
{
    public BcryptSecretKey(BcryptKeySpec scryptKeySpec, Hash hash)
    {
        super(scryptKeySpec, hash);
    }

    @Override
    public String getAlgorithm()
    {
        return "scrypt";
    }
}
