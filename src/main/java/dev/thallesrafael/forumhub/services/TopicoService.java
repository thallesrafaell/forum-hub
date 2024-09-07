package dev.thallesrafael.forumhub.services;

import dev.thallesrafael.forumhub.domain.DTO.*;
import dev.thallesrafael.forumhub.domain.Topico;
import dev.thallesrafael.forumhub.repositories.TopicoRepository;
import dev.thallesrafael.forumhub.validations.ValidadorJwtInvalidado;
import dev.thallesrafael.forumhub.validations.topicos.ValidaTopicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ValidaTopicos validaTopicos;

    @Autowired
    private ValidadorJwtInvalidado validadorJwtInvalidado;

    public Topico cadastrar(TopicoCadastroDTO dados, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        validaTopicos.validarDuplicados(dados);


        var curso = cursoService.cursoPorId(dados.idCurso(), token);
        var autor = usuarioService.usuarioPorId(Long.parseLong(token.getName()), token);

        var novoTopico = new Topico(dados, curso, autor);

        return  repository.save(novoTopico);
    }

    public List<TopicoResponseDto> listarTodos(JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        return repository.findAll()
                .stream()
                .map(TopicoResponseDto::new)
                .toList();
    }

    public TopicoDetalhamentoDto listarPorId(Long id, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        var topico = repository.findById(id).orElseThrow(()-> new RuntimeException("Tópico não encontrado ou id inválido"));
        var list = topico.getRespostas().stream().map(resposta -> new RespostaResponseDto(resposta)).toList();
        return new TopicoDetalhamentoDto(topico,list);
    }

    public Topico atualizarTopico(TopicoAttDTO dados, JwtAuthenticationToken token) {
       validadorJwtInvalidado.validar(token.getToken().getTokenValue());
       validaTopicos.validarUsuarioTopico(token, dados);
       validaTopicos.validarAtualizacaoDuplicado(dados);
       var topico = repository.findById(dados.id()).orElseThrow(()-> new RuntimeException("Tópico não encontrado ou id inválido"));
       topico.atulizarInformacoes(dados);
       return repository.save(topico);
    }

    public void deletar(Long id, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        repository.deleteById(id);
    }
}
