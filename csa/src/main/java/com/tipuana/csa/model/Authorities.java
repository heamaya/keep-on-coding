package com.tipuana.csa.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class Authorities {
	private static BasicAuthority basicAuthority;
	private static IntermediateAuthority intermediateAuthority;
	private static AdvancedAuthority advancedAuthority;
	private static FinanceAuthority financeAuthority;
	private static UsersAuthority usersAuthority;
	
	static {
		basicAuthority = new BasicAuthority();
		intermediateAuthority = new IntermediateAuthority();
		advancedAuthority = new AdvancedAuthority();
		financeAuthority = new FinanceAuthority();
		usersAuthority = new UsersAuthority();
	}
	
	public static List<GrantedAuthority> getAuthorities(Role role) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		if(role == Role.BASICADMIN) {
			grantedAuthorities.add(basicAuthority);
		} else if(role == Role.ADMIN) {
			grantedAuthorities.add(basicAuthority);
			grantedAuthorities.add(intermediateAuthority);
		} else if(role == Role.SUPERADMIN) {
			grantedAuthorities.add(basicAuthority);
			grantedAuthorities.add(intermediateAuthority);
			grantedAuthorities.add(advancedAuthority);
			grantedAuthorities.add(financeAuthority);
		} else if(role == Role.ROOT) {
			grantedAuthorities.add(basicAuthority);
			grantedAuthorities.add(intermediateAuthority);
			grantedAuthorities.add(advancedAuthority);
			grantedAuthorities.add(usersAuthority);
		}
		
		return grantedAuthorities;
	}
	
}