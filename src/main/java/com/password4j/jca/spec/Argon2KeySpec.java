package com.password4j.jca.spec;

import com.password4j.Argon2Function;
import com.password4j.types.Argon2;


public class Argon2KeySpec extends AbstractKeySpec
{
    private final int memory;

    private final int iterations;

    private final int parallelization;

    private final int keyLength;

    private final Argon2 type;

    private int version;

    public Argon2KeySpec(char[] password, byte[] salt, int memory, int iterations, int parallelization, int keyLength, Argon2 type)
    {
        super(password, salt);
        this.memory = memory;
        this.iterations = iterations;
        this.parallelization = parallelization;
        this.keyLength = keyLength;
        this.type = type;
        this.version = Argon2Function.ARGON2_VERSION_13;
    }

    public Argon2KeySpec(char[] password, byte[] salt, int memory, int iterations, int parallelization, int keyLength, Argon2 type, int version)
    {
       this(password, salt, memory, iterations, parallelization, keyLength, type);
       this.version = version;
    }

    public int getMemory()
    {
        return memory;
    }

    public int getIterations()
    {
        return iterations;
    }

    public int getParallelization()
    {
        return parallelization;
    }

    public int getKeyLength()
    {
        return keyLength;
    }

    public Argon2 getType()
    {
        return type;
    }

    public int getVersion()
    {
        return version;
    }
}
