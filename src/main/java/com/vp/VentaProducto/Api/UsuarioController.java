package com.vp.VentaProducto.Api;
import com.vp.VentaProducto.Dtos.Usuario.AuthRequest;
import com.vp.VentaProducto.Dtos.Usuario.UsuarioInfo;
import com.vp.VentaProducto.servicessecurity.JwtService;
import com.vp.VentaProducto.servicessecurity.UsuarioInfoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
    private final UsuarioInfoServicio service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    UsuarioController(UsuarioInfoServicio service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioInfo> addNewUser(@RequestBody UsuarioInfo userInfo) {
        UsuarioInfo response = service.addUser(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.Email(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.Email());
            return ResponseEntity.ok(token);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}