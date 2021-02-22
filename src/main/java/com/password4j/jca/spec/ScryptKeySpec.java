package com.password4j.jca.spec;

public class ScryptKeySpec extends AbstractKeySpec
{
    private final int workFactor;

    private final int resources;

    private final int parallelization;

    private final int keyLength;

    public ScryptKeySpec(char[] password, byte[] salt, int workFartor, int resources, int parallelization, int keyLength)
    {
        super(password, salt);
        this.workFactor = workFartor;
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
}
