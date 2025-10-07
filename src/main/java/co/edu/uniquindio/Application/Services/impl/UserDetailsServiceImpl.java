package co.edu.uniquindio.Application.Services.impl;

import co.edu.uniquindio.Application.Model.Usuario;
import co.edu.uniquindio.Application.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String idStr) throws UsernameNotFoundException {
        Long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("ID inválido: " + idStr);
        }
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRol().name()));

        return new org.springframework.security.core.userdetails.User(
                usuario.getId().toString(),
                usuario.getPassword(),
                authorities
        );
    }
}