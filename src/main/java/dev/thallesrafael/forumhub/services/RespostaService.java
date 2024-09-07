package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.domain.DTO.RespostaAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.RespostaDTO;
import dev.thallesrafael.forumhub.domain.DTO.RespostaResponseDto;
import dev.thallesrafael.forumhub.domain.Resposta;
import dev.thallesrafael.forumhub.repositories.RespostaRepository;
import dev.thallesrafael.forumhub.repositories.TopicoRepository;
import dev.thallesrafael.forumhub.validations.ValidadorJwtInvalidado;
import dev.thallesrafael.forumhub.validations.topicos.ValidarRespostas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
    private TopicoRepository topicoRepository;

    @Autowired
    private ValidarRespostas validar;

    @Autowired
    private ValidadorJwtInvalidado validadorJwtInvalidado;

    public Resposta cadastrar(RespostaDTO dados, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        var idAutor =Long.parseLong(token.getName());
        validar.validarDuplicata(dados, token);
        var autor = usuarioService.usuarioPorId(idAutor, token);
        var topico = topicoRepository.findById(Long.parseLong(token.getName())).orElseThrow(()-> new RuntimeException("Tópico não encontrado ou id inválido"));
        var resposta = new Resposta(null, dados.mensagem(), topico, LocalDateTime.now(), autor);

        return repository.save(resposta);

    }

    public List<RespostaResponseDto> listar(JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        return repository.findAll().stream().map(RespostaResponseDto::new).toList();
    }

    public Resposta listarPorId(Long id,JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado ou inválido"));
    }

    public Resposta atualizar(RespostaAttDTO dados, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        var idAutor =Long.parseLong(token.getName());
        validar.validarAutorResposta(dados.id(), token);
        validar.validarDuplicataAtt(dados, token);
        var reposta = listarPorId(dados.id(),token);
        reposta.atualizarInformacoes(dados, idAutor);
        return  repository.save(reposta);

    }


    public void deletar(Long id, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        repository.deleteById(id);
    }
}
