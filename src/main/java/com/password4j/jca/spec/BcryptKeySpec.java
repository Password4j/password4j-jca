package com.password4j.jca.spec;

import com.password4j.types.BCrypt;


public class BcryptKeySpec extends AbstractKeySpec
{
    private final BCrypt minor;

    private final int logRounds;


    public BcryptKeySpec(char[] password, byte[] salt, char minor, int logRounds)
    {
        super(password, salt);
        BCrypt bcrypt =  BCrypt.valueOf(minor);
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

    public BCrypt getBcrypt()
    {
        return minor;
    }

    public int getLogRounds()
    {
        return logRounds;
    }
}
