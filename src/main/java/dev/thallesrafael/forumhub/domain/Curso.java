package dev.thallesrafael.forumhub.domain;

import dev.thallesrafael.forumhub.controllers.CusoCadastroDto;
import dev.thallesrafael.forumhub.domain.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "cursos")
@Entity(name = "Cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(CusoCadastroDto dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}
