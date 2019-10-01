package com.tipuana.csa.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tipuana.csa.model.Role;
import com.tipuana.csa.model.User;


public interface UserDAO extends GenericDAO<User, Long>, UserDetailsService {

    public abstract List<User> findAllOrderByUser();

    public abstract List<User> find(final User user);

    public abstract List<User> find(final Role role);

    public abstract UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, DataAccessException;
}
