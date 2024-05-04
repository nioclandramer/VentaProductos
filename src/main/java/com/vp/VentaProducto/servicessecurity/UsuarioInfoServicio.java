package com.vp.VentaProducto.servicessecurity;
import com.vp.VentaProducto.Dtos.Usuario.UsuarioInfo;
import com.vp.VentaProducto.Entidades.Usuario;
import com.vp.VentaProducto.Repositorios.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioInfoServicio implements UserDetailsService {
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioInfoServicio(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> userDetail = userRepository.findByEmail(email);
        return userDetail.map(UsuarioInfoDetalle::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public UsuarioInfo addUser(UsuarioInfo userInfo) {
        Usuario user = new Usuario(null, userInfo.name(), userInfo.Email(), passwordEncoder.encode(userInfo.password()), userInfo.roles() );
        user = userRepository.save(user);
        return new UsuarioInfo(user.getName(), user.getEmail(), userInfo.password(), user.getRoles());

    }
}