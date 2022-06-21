package com.password4j.jca.spi;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;

import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.ScryptFunction;
import com.password4j.SecureString;
import com.password4j.jca.keys.ScryptSecretKey;
import com.password4j.jca.spec.ScryptKeySpec;


public class ScryptSpi extends AbstractSecretKeyFactorySpi
{
    @Override
    protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException
    {
        if (keySpec instanceof ScryptKeySpec)
        {
            ScryptKeySpec spec = (ScryptKeySpec) keySpec;
            ScryptFunction scrypt = ScryptFunction
                    .getInstance(spec.getWorkFactor(), spec.getResources(), spec.getParallelization(), spec.getKeyLength());
            SecureString password = new SecureString(spec.getPassword());
            Hash hash = Password.hash(password).addSalt(new String(spec.getSalt(), DEFAULT_CHARSET)).with(scrypt);
            return new ScryptSecretKey(spec, hash);
        }
        else
        {
            throw new InvalidKeySpecException("Invalid key spec");
        }
    }

    @Override
    protected KeySpec engineGetKeySpec(SecretKey key, Class<?> keySpecCl) throws InvalidKeySpecException
    {
        if (key instanceof ScryptSecretKey)
        {
            if ((keySpecCl != null) && ScryptKeySpec.class.isAssignableFrom(keySpecCl))
            {
                ScryptSecretKey scryptKey = (ScryptSecretKey) key;
                ScryptFunction function = (ScryptFunction) scryptKey.getHash().getHashingFunction();

                return new ScryptKeySpec(scryptKey.getPassword(), scryptKey.getHash().getSalt().getBytes(),
                        function.getWorkFactor(), function.getResources(), function.getParallelization(),
                        function.getDerivedKeyLength());
            }
            else
            {
                throw new InvalidKeySpecException("Invalid key spec");
            }
        }
        else
        {
            throw new InvalidKeySpecException("Invalid key format/algorithm");
        }
    }

    @Override
    protected SecretKey engineTranslateKey(SecretKey key) throws InvalidKeyException
    {
        if (key != null && "scrypt".equalsIgnoreCase(key.getAlgorithm()) && "RAW".equalsIgnoreCase(key.getFormat()) && key instanceof ScryptSecretKey)
        {
            return key;
        }
        throw new InvalidKeyException("Invalid key format/algorithm");
    }
}
