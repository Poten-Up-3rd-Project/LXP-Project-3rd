package com.lxp.auth.infrastructure.security.adapter;

import com.lxp.auth.application.local.port.required.UserAuthSearchPort;
import com.lxp.auth.application.local.port.required.dto.AuthDomainInfo;
import com.lxp.auth.infrastructure.security.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthSearchPort userAuthSearchPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthDomainInfo authDomainInfo = userAuthSearchPort.retrieveAuthorityByEmail(username);

        Collection<? extends GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority(authDomainInfo.role())
        );

        return new CustomUserDetails(
            authDomainInfo.userId(),
            authDomainInfo.email(),
            authDomainInfo.email(),
            "",
            authorities
        );
    }
}
