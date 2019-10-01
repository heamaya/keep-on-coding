package com.tipuana.csa.interceptor;

import java.lang.reflect.Method;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tipuana.csa.model.User;


@SuppressWarnings("serial")
public class SpringSecurityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		
		if(currentUser != null) {
			
			Class<?> actionClass = action.getClass();
			boolean isDone = false;
			
			while((actionClass = actionClass.getSuperclass()) != null && !isDone) {
				
				for(Method method : actionClass.getDeclaredMethods()) {
					
					if(method.getAnnotation(SpringSecurityPrincipal.class) != null && currentUser.getPrincipal() instanceof User) {
						method.invoke(action, currentUser.getPrincipal());
						isDone = true;
						
						break;
					}
				}
				
			}				
			
		} 	
		
		return actionInvocation.invoke();
	}

}
