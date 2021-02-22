package com.password4j.jca.spi;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;

import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.SecureString;
import com.password4j.jca.keys.Argon2SecretKey;
import com.password4j.jca.spec.Argon2KeySpec;


public class Argon2Spi extends AbstractSecretKeyFactorySpi
{
    @Override
    protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException
    {
        if (keySpec instanceof Argon2KeySpec)
        {
            Argon2KeySpec spec = (Argon2KeySpec) keySpec;
            Argon2Function argon2 = Argon2Function.getInstance(
                    spec.getMemory(), spec.getIterations(), spec.getParallelization(), spec.getKeyLength(), spec.getType(), spec.getVersion());

            SecureString password = new SecureString(spec.getPassword());
            Hash hash = Password.hash(password).addSalt(new String(spec.getSalt(), DEFAULT_CHARSET)).with(argon2);
            return new Argon2SecretKey(spec, hash);
        }
        else
        {
            throw new InvalidKeySpecException("Invalid key spec");
        }
    }

    @Override
    protected KeySpec engineGetKeySpec(SecretKey key, Class<?> keySpecCl) throws InvalidKeySpecException
    {
        if (key instanceof Argon2SecretKey)
        {
            if ((keySpecCl != null) && Argon2KeySpec.class.isAssignableFrom(keySpecCl))
            {
                Argon2SecretKey argon2Key = (Argon2SecretKey) key;
                Argon2Function function = (Argon2Function) argon2Key.getHash().getHashingFunction();

                return new Argon2KeySpec(argon2Key.getPassword(), argon2Key.getHash().getSalt().getBytes(),
                        function.getMemory(), function.getIterations(), function.getParallelism(),
                        function.getOutputLength(), function.getVariant(), function.getVersion());
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
        if (key != null && "argon2".equalsIgnoreCase(key.getAlgorithm()) && "RAW".equalsIgnoreCase(key.getFormat()) && key instanceof Argon2SecretKey)
        {
            return key;
        }
        throw new InvalidKeyException("Invalid key format/algorithm");
    }
}
