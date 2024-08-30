package dev.thallesrafael.forumhub.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.thallesrafael.forumhub.controllers.RespostaAttDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity(name = "Resposta")
@Table(name = "respostas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    @JsonManagedReference
    private Topico topico;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonManagedReference
    private Usuario autor;

    public void atualizarInformacoes(RespostaAttDTO dados) {
        if (dados.mensagem() != null && dados.idAutor().equals(this.autor.getId())){
            this.mensagem = dados.mensagem();
        }
    }
}
