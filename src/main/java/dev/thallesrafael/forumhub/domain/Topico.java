package dev.thallesrafael.forumhub.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.thallesrafael.forumhub.domain.DTO.TopicoAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.TopicoCadastroDTO;
import dev.thallesrafael.forumhub.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "topico")
    @JsonBackReference
    private List<Resposta> respostas;


    public Topico(TopicoCadastroDTO dados, Curso curso, Usuario autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.autor = autor;
        this.curso = curso;
        this.status = Status.ABERTO;

    }

    public void atulizarInformacoes(TopicoAttDTO dados)
    {
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
    }
}
