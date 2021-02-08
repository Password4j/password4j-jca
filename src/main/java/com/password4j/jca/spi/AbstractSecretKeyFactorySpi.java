package com.password4j.jca.spi;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKeyFactorySpi;


public abstract class AbstractSecretKeyFactorySpi extends SecretKeyFactorySpi
{

    protected static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

}
