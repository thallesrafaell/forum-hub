package dev.thallesrafael.forumhub.services;

import dev.thallesrafael.forumhub.domain.DTO.LoginRequest;
import dev.thallesrafael.forumhub.domain.DTO.LoginResponse;
import dev.thallesrafael.forumhub.domain.DTO.UsuarioCadastroDTO;
import dev.thallesrafael.forumhub.domain.Perfil;
import dev.thallesrafael.forumhub.domain.Usuario;
import dev.thallesrafael.forumhub.repositories.PerfilRepository;
import dev.thallesrafael.forumhub.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TokenService {


    private JwtEncoder encoder;

    private UsuarioRepository repository;

    private PerfilRepository perfilRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TokenService(JwtEncoder encoder, UsuarioRepository repository, PerfilRepository perfilRepository, BCryptPasswordEncoder passwordEncoder) {
        this.encoder = encoder;
        this.repository = repository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request){
        var user = repository.findByEmail(request.usuario());

        if(user.isEmpty() || !user.get().loginCorreto(request, passwordEncoder)){
            throw new BadCredentialsException("E-mail ou senha inváidos");
        }
        var agora = Instant.now();
        var expiresIn = 300L;

        var escopo = user.get().getPerfis()
                .stream()
                .map(Perfil::getNome)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("forumHub")
                .subject(user.get().getId().toString())
                .expiresAt(agora.plusSeconds(expiresIn))
                .issuedAt(agora)
                .claim("scope", escopo)
                .build();

        var jwtValue = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(jwtValue, expiresIn);
    }

    public Usuario cadastrar(UsuarioCadastroDTO dados){
        var usuarioDoBanco = repository.findByEmail(dados.email());

        if(usuarioDoBanco.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        var usuario = new Usuario(dados);
        var perfil = perfilRepository.findByNome(Perfil.Valores.PADRAO.name()).get();
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setPerfis(Set.of(perfil));
        repository.save(usuario);
        return usuario;
    }

    }



