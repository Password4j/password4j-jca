package com.password4j.jca.spi;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;

import com.password4j.BCryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.SecureString;
import com.password4j.jca.keys.BcryptSecretKey;
import com.password4j.jca.spec.BcryptKeySpec;


public class BcryptSpi extends AbstractSecretKeyFactorySpi
{
    @Override
    protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException
    {
        if (keySpec instanceof BcryptKeySpec)
        {
            BcryptKeySpec spec = (BcryptKeySpec) keySpec;
            BCryptFunction bcrypt = BCryptFunction
                    .getInstance(spec.getBcrypt(), spec.getLogRounds());
            SecureString password = new SecureString(spec.getPassword());
            Hash hash = Password.hash(password).addSalt(new String(spec.getSalt(), DEFAULT_CHARSET)).with(bcrypt);
            return new BcryptSecretKey(spec, hash);
        }
        else
        {
            throw new InvalidKeySpecException("Invalid key spec");
        }
    }

    @Override
    protected KeySpec engineGetKeySpec(SecretKey key, Class<?> keySpecCl) throws InvalidKeySpecException
    {
        if (key instanceof BcryptSecretKey)
        {
            if ((keySpecCl != null) && BcryptKeySpec.class.isAssignableFrom(keySpecCl))
            {
                BcryptSecretKey bcryptKey = (BcryptSecretKey) key;
                BCryptFunction function = (BCryptFunction) bcryptKey.getHash().getHashingFunction();

                return new BcryptKeySpec(bcryptKey.getPassword(), bcryptKey.getHash().getSalt().getBytes(),
                        function.getType().minor(), function.getLogarithmicRounds());
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
        if (key != null && "bcrypt".equalsIgnoreCase(key.getAlgorithm()) && "RAW".equalsIgnoreCase(key.getFormat()) && key instanceof BcryptSecretKey)
        {
            return key;
        }
        throw new InvalidKeyException("Invalid key format/algorithm");
    }
}
