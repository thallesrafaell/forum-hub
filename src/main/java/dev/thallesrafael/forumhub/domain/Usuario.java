package dev.thallesrafael.forumhub.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.thallesrafael.forumhub.domain.DTO.LoginRequest;
import dev.thallesrafael.forumhub.domain.DTO.UsuarioAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.UsuarioCadastroDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "autor")
    @JsonBackReference
    private List<Topico> topicos;

    @OneToMany(mappedBy = "autor")
    @JsonBackReference
    private List<Resposta> respostas;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "perfis_usuarios",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Perfil> perfis;


    public Usuario(UsuarioCadastroDTO dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }


    public void atualizarInformacoes(UsuarioAttDTO dados){

        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.senha() != null){
            this.senha = dados.senha();
        }
    }

    public boolean loginCorreto(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.senha(), this.senha);
    }
}
