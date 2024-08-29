package dev.thallesrafael.forumhub.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.thallesrafael.forumhub.domain.DTO.DadosCadastroTopico;
import dev.thallesrafael.forumhub.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonManagedReference
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private  Curso curso;


    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = dados.data();
        this.autor = dados.autor();
        this.curso =dados.curso();
        this.status = Status.ABERTO;
    }
}
