package dev.thallesrafael.forumhub.controllers;

import dev.thallesrafael.forumhub.domain.Curso;
import dev.thallesrafael.forumhub.domain.DTO.TopicoCadastroDTO;
import dev.thallesrafael.forumhub.domain.DTO.TopicoResponseDto;
import dev.thallesrafael.forumhub.domain.Perfil;
import dev.thallesrafael.forumhub.domain.Topico;
import dev.thallesrafael.forumhub.domain.Usuario;
import dev.thallesrafael.forumhub.domain.enums.Status;
import dev.thallesrafael.forumhub.services.TopicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TopicoControllerTest {

    @Mock
    private TopicoService topicoService;

    @Mock
    private TopicoController controller;

    @InjectMocks
    private TopicoController topicoController;

    private UriComponentsBuilder uriBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");

    }

    @Test
    void testCadastrar_Success() {
        // Mock data
        TopicoCadastroDTO mockCadastroDto = new TopicoCadastroDTO("teste", "teste", 1L);
        Topico mockTopico = new Topico();
        mockTopico.setId(1L); // Defina um ID simulado
        mockTopico.setTitulo("teste teste");
        mockTopico.setMensagem("teste teste");
        mockTopico.setDataCriacao(LocalDateTime.now());
        mockTopico.setStatus(Status.ABERTO);


        // Simular o curso associado ao tópico
        Curso mockCurso = new Curso();
        mockCurso.setId(1l);
        mockCurso.setNome("Curso de Teste");
        mockTopico.setCurso(mockCurso); // Defina o curso simulado no tópico

        //Simular o usuario associado ao topico:
        Usuario mockUsuario = new Usuario();
        mockUsuario.setId(1L);
        mockUsuario.setNome("teste");
        mockUsuario.setSenha("123");
        mockUsuario.setEmail("teste@teste.com");
        // simular perfil do Usuario:
        Perfil mockPerfil = new Perfil();
        mockPerfil.setId(1L);
        mockPerfil.setNome("ADMIN");
        mockUsuario.setPerfil(Set.of(mockPerfil));
        mockTopico.setAutor(mockUsuario);

        JwtAuthenticationToken mockToken = mock(JwtAuthenticationToken.class);

        // Simular o comportamento do service.cadastrar()
        when(topicoService.cadastrar(any(TopicoCadastroDTO.class), any(JwtAuthenticationToken.class)))
                .thenReturn(mockTopico);

        // Chamar o método a ser testado
        ResponseEntity<TopicoResponseDto> response = topicoController.cadastrar(mockCadastroDto, uriBuilder, mockToken);

        // Verificar o status e o corpo da resposta
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(mockTopico.getId(), response.getBody().id());

        // Verificar se o service.cadastrar foi chamado corretamente
        verify(topicoService, times(1)).cadastrar(mockCadastroDto, mockToken);
    }


}

