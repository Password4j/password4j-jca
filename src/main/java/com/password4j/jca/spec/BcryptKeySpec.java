package com.password4j.jca.spec;

import com.password4j.types.Bcrypt;


public class BcryptKeySpec extends AbstractKeySpec
{
    private final Bcrypt minor;

    private final int logRounds;


    public BcryptKeySpec(char[] password, byte[] salt, char minor, int logRounds)
    {
        super(password, salt);
        Bcrypt bcrypt =  Bcrypt.valueOf(minor);
        if(bcrypt == null)
        {
            throw new IllegalArgumentException("Invalid bcrypt minor");
        }
        this.minor = bcrypt;
        this.logRounds = logRounds;
    }

    public char getMinor()
    {
        return minor.minor();
    }

    public Bcrypt getBcrypt()
    {
        return minor;
    }

    public int getLogRounds()
    {
        return logRounds;
    }

}
