package org.example;

import com.password4j.*;
import com.password4j.jca.keys.Argon2SecretKey;
import com.password4j.jca.keys.BcryptSecretKey;
import com.password4j.jca.keys.ScryptSecretKey;
import com.password4j.jca.providers.Password4jProvider;
import com.password4j.jca.spec.Argon2KeySpec;
import com.password4j.jca.spec.BcryptKeySpec;
import com.password4j.jca.spec.ScryptKeySpec;
import com.password4j.types.Argon2;
import com.password4j.types.BCrypt;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;


public class ProviderTest
{

    private static final char[] PASSWORD = {'p', 'a', 's', 's', '4', 'J', '#'};

    private static final String SALT_STRING = "$2a$12$k42ZFHFWqBp3vWli.nIn8u";

    private static final byte[] SALT = SALT_STRING.getBytes();

    @Before
    public void setup()
    {
        Security.setProperty("crypto.policy", "unlimited");
        Password4jProvider provider4  = new Password4jProvider();
        Security.addProvider(provider4);
    }

    @Test
    public void testArgon2() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException
    {
        // GIVEN
        int memory = 4096;
        int iterations = 50;
        int parallelization = 2;
        int length = 64;
        Argon2 type = Argon2.D;
        int version = Argon2Function.ARGON2_VERSION_10;

        // WHEN
        SecretKeyFactory factory = SecretKeyFactory.getInstance("argon2");
        Argon2KeySpec spec = new Argon2KeySpec(PASSWORD, SALT, memory, iterations, parallelization, length, type, version);
        SecretKey key =  factory.generateSecret(spec);

        Argon2Function argon2Function = Argon2Function.getInstance(memory, iterations, parallelization, length, type, version);
        Hash hash = Password.hash(new SecureString(PASSWORD)).addSalt(SALT_STRING).with(argon2Function);

        Argon2KeySpec ks = (Argon2KeySpec) factory.getKeySpec(key, Argon2KeySpec.class);
        Argon2SecretKey translated = (Argon2SecretKey) factory.translateKey(key);

        // THEN
        assertEquals("argon2", key.getAlgorithm());
        assertTrue(key instanceof Argon2SecretKey);
        assertEquals(argon2Function, ((Argon2SecretKey) key).getHash().getHashingFunction());
        assertEquals(hash, ((Argon2SecretKey) key).getHash());
        assertArrayEquals(hash.getBytes(), key.getEncoded());
        assertArrayEquals(PASSWORD, ((Argon2SecretKey) key).getPassword());
        assertEquals(spec, ks);
        assertEquals(key, translated);
    }

    @Test
    public void testScrypt() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException
    {
        // GIVEN
        int workFactor = 1024;
        int resources = 8;
        int parallelization = 1;
        int length = 64;

        // WHEN
        SecretKeyFactory factory = SecretKeyFactory.getInstance("scrypt");
        ScryptKeySpec spec = new ScryptKeySpec(PASSWORD, SALT, workFactor, resources, parallelization, length);
        SecretKey key =  factory.generateSecret(spec);

        SCryptFunction scryptFunction = SCryptFunction.getInstance(workFactor, resources, parallelization, length);
        Hash hash = Password.hash(new SecureString(PASSWORD)).addSalt(SALT_STRING).with(scryptFunction);

        ScryptKeySpec ks = (ScryptKeySpec) factory.getKeySpec(key, ScryptKeySpec.class);
        ScryptSecretKey translated = (ScryptSecretKey) factory.translateKey(key);

        // THEN
        assertEquals("scrypt", key.getAlgorithm());
        assertTrue(key instanceof ScryptSecretKey);
        assertEquals(scryptFunction, ((ScryptSecretKey) key).getHash().getHashingFunction());
        assertEquals(hash, ((ScryptSecretKey) key).getHash());
        assertArrayEquals(hash.getBytes(), key.getEncoded());
        assertArrayEquals(PASSWORD, ((ScryptSecretKey) key).getPassword());
        assertEquals(spec, ks);
        assertEquals(key, translated);
    }

    @Test
    public void testBcrypt() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException
    {
        // GIVEN
        int rounds = 10;
        BCrypt version = BCrypt.A;

        // WHEN
        SecretKeyFactory factory = SecretKeyFactory.getInstance("bcrypt");
        BcryptKeySpec spec = new BcryptKeySpec(PASSWORD, SALT, version.minor(), rounds);
        SecretKey key =  factory.generateSecret(spec);

        BCryptFunction bcryptFunction = BCryptFunction.getInstance(version, rounds);
        Hash hash = Password.hash(new SecureString(PASSWORD)).addSalt(SALT_STRING).with(bcryptFunction);

        BcryptKeySpec ks = (BcryptKeySpec) factory.getKeySpec(key, BcryptKeySpec.class);
        BcryptSecretKey translated = (BcryptSecretKey) factory.translateKey(key);

        // THEN
        assertEquals("bcrypt", key.getAlgorithm());
        assertTrue(key instanceof BcryptSecretKey);
        assertEquals(bcryptFunction, ((BcryptSecretKey) key).getHash().getHashingFunction());
        assertEquals(hash, ((BcryptSecretKey) key).getHash());
        assertArrayEquals(hash.getBytes(), key.getEncoded());
        assertArrayEquals(PASSWORD, ((BcryptSecretKey) key).getPassword());
        assertEquals(spec, ks);
        assertEquals(key, translated);
    }
}
