package dev.thallesrafael.forumhub.services;

import dev.thallesrafael.forumhub.domain.DTO.DadosCadastroTopico;
import dev.thallesrafael.forumhub.domain.DTO.TopicoAttDTO;
import dev.thallesrafael.forumhub.domain.DTO.TopicoCadastroDTO;
import dev.thallesrafael.forumhub.domain.Topico;
import dev.thallesrafael.forumhub.repositories.TopicoRepository;
import dev.thallesrafael.forumhub.validations.topicos.ValidaTopicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Topico cadastrar(TopicoCadastroDTO dados){
        validaTopicos.validarDuplicados(dados);

        var curso = cursoService.cursoPorId(dados.idCurso());
        var autor = usuarioService.usuarioPorId(dados.idAutor());
        var dadosCadastro = new DadosCadastroTopico(dados.titulo(), dados.mensagem(), LocalDateTime.now(), autor, curso);
        Topico topico = new Topico(dadosCadastro);

        return  repository.save(topico);
    }

    public List<Topico> listarTodos() {
        return repository.findAll();
    }

    public Topico listarPorId(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Tópico não encontrado ou id inválido"));
    }

    public Topico atualizarTopico(TopicoAttDTO dados) {
        validaTopicos.validarAtualizacaoDuplicado(dados);
       var topico = listarPorId(dados.id());
       topico.atulizarInformacoes(dados);
       return repository.save(topico);

    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
