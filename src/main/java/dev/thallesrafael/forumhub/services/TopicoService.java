package dev.thallesrafael.forumhub.services;

import dev.thallesrafael.forumhub.domain.DTO.RespostaResponseDto;
import dev.thallesrafael.forumhub.domain.DTO.TopicoAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.TopicoCadastroDTO;
import dev.thallesrafael.forumhub.domain.DTO.TopicoDetalhamentoDto;
import dev.thallesrafael.forumhub.domain.Topico;
import dev.thallesrafael.forumhub.repositories.TopicoRepository;
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
    ValidaTopicos validaTopicos;

    public Topico cadastrar(TopicoCadastroDTO dados, JwtAuthenticationToken token){
        validaTopicos.validarDuplicados(dados);

        var curso = cursoService.cursoPorId(dados.idCurso());
        var autor = usuarioService.usuarioPorId(Long.parseLong(token.getName()));

        var novoTopico = new Topico(dados, curso, autor);

        return  repository.save(novoTopico);
    }

    public List<Topico> listarTodos() {
        return repository.findAll();
    }

    public TopicoDetalhamentoDto listarPorId(Long id) {

        var topico = repository.findById(id).orElseThrow(()-> new RuntimeException("Tópico não encontrado ou id inválido"));
        var list = topico.getRespostas().stream().map(resposta -> new RespostaResponseDto(resposta)).toList();
        return new TopicoDetalhamentoDto(topico,list);
    }

    public Topico atualizarTopico(TopicoAttDTO dados, JwtAuthenticationToken token) {
       validaTopicos.validarUsuarioTopico(token, dados);
       validaTopicos.validarAtualizacaoDuplicado(dados);
       var topico = repository.findById(dados.id()).orElseThrow(()-> new RuntimeException("Tópico não encontrado ou id inválido"));
       topico.atulizarInformacoes(dados);
       return repository.save(topico);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
