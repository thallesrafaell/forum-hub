package dev.thallesrafael.forumhub.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "perfis")
@Getter
@Setter
public class Perfil {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String nome;


    public enum Valores{

        ADMIN(1L),
        MODERADOR(2L),
        PADRAO(3L);

        long roleid;

        Valores(long roleid) {
            this.roleid = roleid;
        }
    }
}
