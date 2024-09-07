package dev.thallesrafael.forumhub.services;


import dev.thallesrafael.forumhub.domain.DTO.CusoCadastroDto;
import dev.thallesrafael.forumhub.domain.Curso;
import dev.thallesrafael.forumhub.repositories.CursoRepository;
import dev.thallesrafael.forumhub.validations.ValidadorJwtInvalidado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private ValidadorJwtInvalidado validadorJwtInvalidado;




    public Curso cadastrar(CusoCadastroDto dados, JwtAuthenticationToken token){
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        return repository.save(new Curso(dados));
    }

    public Curso cursoPorId(Long id, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Id do curso invalido"));
    }

    public Curso atualizar(Curso dados, JwtAuthenticationToken token){
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        Curso curso = cursoPorId(dados.getId(),token);
        curso.atulizarInformacoes(dados);
        repository.save(curso);
        return curso;
    }

    public void deletarPorId(Long id, JwtAuthenticationToken token) {
        validadorJwtInvalidado.validar(token.getToken().getTokenValue());
        repository.deleteById(id);
    }
}
