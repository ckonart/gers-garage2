package gersgarage2.GersGarage2.service;

import org.springframework.security.core.GrantedAuthority;
import java.io.Serializable;
import java.util.Collection;

public interface UserDetailsService extends Serializable {

    public Collection<? extends GrantedAuthority> getAuthorities();

    public String getPassword();

    public String getUsername();

    public boolean isAccountNonExpired();

    public boolean isAccountNonLocked();

    public boolean isCredentialsNonExpired();

    public boolean isEnabled();
}
