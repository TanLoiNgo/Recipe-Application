/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <Get User details from dependency security, using granted authority>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.config;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.User;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getAccount();
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

    public Long getId() {
        return user.getId();
    }
}
