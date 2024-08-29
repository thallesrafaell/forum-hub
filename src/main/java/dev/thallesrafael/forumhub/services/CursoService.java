package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.controllers.CusoCadastroDto;
import dev.thallesrafael.forumhub.domain.Curso;
import dev.thallesrafael.forumhub.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;


    public Curso cadastrar(CusoCadastroDto dados){
        return repository.save(new Curso(dados));
    }

    public Curso cursoPorId(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Id do curso invalido"));
    }

    public Curso atualizar(Curso dados){
        Curso curso = cursoPorId(dados.getId());
        curso.atulizarInformacoes(dados);
        repository.save(curso);
        return curso;
    }
}
