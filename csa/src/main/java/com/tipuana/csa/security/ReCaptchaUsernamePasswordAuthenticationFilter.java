package com.tipuana.csa.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class ReCaptchaUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private ReCaptcha reCaptcha;

    public ReCaptchaUsernamePasswordAuthenticationFilter() {
    
    }
    
    

    @Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
        String remoteAddress = request.getRemoteAddr();
        String challenge = request.getParameter("recaptcha_challenge_field");
        String responseStr = request.getParameter("recaptcha_response_field");
        
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddress, challenge, responseStr);
        
        if(reCaptchaResponse.isValid()) {
        	return super.attemptAuthentication(request, response);
        } else {
        	throw new RecaptchaValidationException("Palabras Recaptcha incorrectas.");
        }
	}

    public ReCaptcha getReCaptcha() {
        return reCaptcha;
    }

    public void setReCaptcha(ReCaptcha reCaptcha) {
        this.reCaptcha = reCaptcha;
    }
}