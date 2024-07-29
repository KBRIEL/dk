package com.dk.modelo.Security;



import com.dk.modelo.Role;
import com.dk.modelo.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;



@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Usuario usuario;
 /*

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }*/
    //TODO
   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí asumimos que el rol es único y no una colección.
        return Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol().toString()));
    }
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
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

    public String getNombre(){
        return usuario.getName();
    }

    public Role getRole() {
        return usuario.getRol();
    }
}
