package org.example;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.SCryptFunction;
import com.password4j.SecureString;
import com.password4j.jca.keys.ScryptSecretKey;
import com.password4j.jca.spec.ScryptKeySpec;
import org.junit.Before;
import org.junit.Test;

import com.password4j.jca.providers.Password4jProvider;

import static org.junit.Assert.*;


public class ProviderTest
{

    private static final char[] PASSWORD = {'p', 'a', 's', 's', '4', 'J', '#'};

    private static final byte[] SALT = {100, 101, 110, 111, 2};

    @Before
    public void setup()
    {
        Security.setProperty("crypto.policy", "unlimited");
        Password4jProvider provider4  = new Password4jProvider();
        Security.addProvider(provider4);
    }

    @Test
    public void testScrypt() throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // GIVEN
        int workFactor = 1024;
        int resources = 8;
        int parallelization = 1;
        int length = 64;

        // WHEN
        SecretKeyFactory sk = SecretKeyFactory.getInstance("scrypt");
        ScryptKeySpec spec = new ScryptKeySpec(PASSWORD, SALT, workFactor, resources, parallelization, length);
        SecretKey key =  sk.generateSecret(spec);

        SCryptFunction scryptFunction = SCryptFunction.getInstance(workFactor, resources, parallelization, length);
        Hash hash = Password.hash(new SecureString(PASSWORD)).addSalt(new String(SALT)).with(scryptFunction);

        // THEN
        assertEquals("scrypt", key.getAlgorithm());
        assertTrue(key instanceof ScryptSecretKey);
        assertEquals(scryptFunction, ((ScryptSecretKey) key).getHash().getHashingFunction());
        assertEquals(hash, ((ScryptSecretKey) key).getHash());
        assertArrayEquals(hash.getBytes(), key.getEncoded());
        assertArrayEquals(PASSWORD, ((ScryptSecretKey) key).getPassword());
    }
}
