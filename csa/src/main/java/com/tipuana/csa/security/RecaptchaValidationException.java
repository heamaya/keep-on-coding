package com.tipuana.csa.security;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class RecaptchaValidationException extends AuthenticationException {

    public RecaptchaValidationException(String msg) {
        super(msg);
    }
}
