package com.anjuxing.platform.security.handler;


import com.anjuxing.platform.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author xiongt
 * @Description
 */
public class UserDetailsImpl implements UserDetails {

    private User user;


    public UserDetailsImpl(User user){

        this.user = user;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //初始化权限集合
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();

        collection.add(new SimpleGrantedAuthority("ROLE_USER"));

        return collection;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
            return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}