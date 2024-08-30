package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.domain.DTO.RespostaAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.RespostaDTO;
import dev.thallesrafael.forumhub.domain.Resposta;
import dev.thallesrafael.forumhub.repositories.RespostaRepository;
import dev.thallesrafael.forumhub.validations.topicos.ValidarRespostas;
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

    @Autowired
    private ValidarRespostas validar;

    public Resposta cadastrar(RespostaDTO dados) {
        validar.validarDuplicata(dados);
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

    public Resposta atualizar(RespostaAttDTO dados) {
        validar.validarDuplicataAtt(dados);
        var reposta = listarPorId(dados.id());
        reposta.atualizarInformacoes(dados);
        return  repository.save(reposta);

    }


    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
