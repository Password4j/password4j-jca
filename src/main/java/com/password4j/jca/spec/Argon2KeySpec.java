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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Argon2KeySpec)) return false;

        Argon2KeySpec that = (Argon2KeySpec) o;

        if (memory != that.memory) return false;
        if (iterations != that.iterations) return false;
        if (parallelization != that.parallelization) return false;
        if (keyLength != that.keyLength) return false;
        if (version != that.version) return false;
        return type == that.type;
    }

    @Override
    public int hashCode()
    {
        int result = memory;
        result = 31 * result + iterations;
        result = 31 * result + parallelization;
        result = 31 * result + keyLength;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
