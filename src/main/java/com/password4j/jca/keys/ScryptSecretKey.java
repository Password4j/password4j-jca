package com.password4j.jca.keys;

import com.password4j.Hash;
import com.password4j.jca.spec.ScryptKeySpec;


public class ScryptSecretKey extends AbstractSecretKey<ScryptKeySpec>
{
    public ScryptSecretKey(ScryptKeySpec scryptKeySpec, Hash hash)
    {
        super(scryptKeySpec, hash);
    }

    @Override
    public String getAlgorithm()
    {
        return "scrypt";
    }
}
