package dev.thallesrafael.forumhub.config;

import dev.thallesrafael.forumhub.domain.Usuario;
import dev.thallesrafael.forumhub.repositories.PerfilRepository;
import dev.thallesrafael.forumhub.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminConfig implements CommandLineRunner {
    private final PerfilRepository perfilRepository;

    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public AdminConfig(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var perfilAdmin =  perfilRepository.findById(1L).get();
        var userAdmin = usuarioRepository.findByEmail("admin@forumhub.com");

        if(userAdmin == null){
            var user = new Usuario();
                user.setNome("admin");
                user.setEmail("admin@forumhub.com");
                user.setSenha(passwordEncoder.encode("12345"));
                user.setPerfis(Set.of(perfilAdmin));
                usuarioRepository.save(user);
        } else {
            System.out.println("admin já existe");
        }
    }
}
