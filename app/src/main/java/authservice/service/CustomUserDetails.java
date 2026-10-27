package authservice.service;

import authservice.entities.UserInfo;
import authservice.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo
        implements UserDetails {

    // this is the collection of authorities that will be granted to the user. It is a collection of GrantedAuthority objects, which represent the roles or permissions assigned to the user.
    // We are using Functional programming to map the roles from the UserInfo entity to GrantedAuthority objects, which are then stored in this collection.
    Collection<? extends GrantedAuthority> authorities;

    private String username;

    private String password;

    public CustomUserDetails(UserInfo byUsername) {

        this.username = byUsername.getUsername();
        this.password = byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        for (UserRole role : byUsername.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
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