package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.domain.DTO.RespostaDTO;
import dev.thallesrafael.forumhub.domain.Resposta;
import dev.thallesrafael.forumhub.domain.Usuario;
import dev.thallesrafael.forumhub.repositories.RespostaRepository;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TopicoService topicoService;

    public Resposta cadastrar(RespostaDTO dados) {
        var autor = usuarioService.usuarioPorId(dados.idAutor());
        var topico = topicoService.listarPorId(dados.idTopico());
        var resposta = new Resposta(null, dados.mensagem(), topico, LocalDateTime.now(), autor);

        return repository.save(resposta);

    }

    public List<Resposta> listar() {
        return repository.findAll().stream().toList();
    }

    public Resposta listarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado ou inválido"));
    }
}
