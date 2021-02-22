package com.password4j.jca.spec;

public class ScryptKeySpec extends AbstractKeySpec
{
    private final int workFactor;

    private final int resources;

    private final int parallelization;

    private final int keyLength;

    public ScryptKeySpec(char[] password, byte[] salt, int workFactor, int resources, int parallelization, int keyLength)
    {
        super(password, salt);
        this.workFactor = workFactor;
        this.resources = resources;
        this.parallelization = parallelization;
        this.keyLength = keyLength;
    }

    public int getWorkFactor()
    {
        return workFactor;
    }

    public int getResources()
    {
        return resources;
    }

    public int getParallelization()
    {
        return parallelization;
    }

    public int getKeyLength()
    {
        return keyLength;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ScryptKeySpec)) return false;

        ScryptKeySpec that = (ScryptKeySpec) o;

        if (workFactor != that.workFactor) return false;
        if (resources != that.resources) return false;
        if (parallelization != that.parallelization) return false;
        return keyLength == that.keyLength;
    }

    @Override
    public int hashCode()
    {
        int result = workFactor;
        result = 31 * result + resources;
        result = 31 * result + parallelization;
        result = 31 * result + keyLength;
        return result;
    }
}
