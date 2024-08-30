package dev.thallesrafael.forumhub.domain;

import dev.thallesrafael.forumhub.domain.DTO.CursoAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.CusoCadastroDto;
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

    public Curso(CursoAttDTO dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }

    public void atulizarInformacoes(Curso dados){
        if(dados.nome != null){
            this.nome = dados.getNome();
        }
        if(dados.categoria != null){
            this.categoria =dados.getCategoria();
        }

    }
}
