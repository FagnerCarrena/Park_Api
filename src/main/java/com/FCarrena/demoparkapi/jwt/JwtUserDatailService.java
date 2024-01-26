package com.FCarrena.demoparkapi.jwt;

import com.FCarrena.demoparkapi.entity.Usuario;
import com.FCarrena.demoparkapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class JwtUserDatailService implements UserDetailsService {

    private final UsuarioService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorUsername(username);
        return new JwtUserDatail(usuario);
    }

    public JwtToken getTokenAuthenticated(String username){
Usuario.Role role = usuarioService.buscarRolePorUsername(username);
return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));

    }




}
