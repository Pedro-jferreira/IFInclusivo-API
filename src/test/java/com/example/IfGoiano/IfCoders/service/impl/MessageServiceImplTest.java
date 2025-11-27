package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.MessageMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.*;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.MessageRepository;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @InjectMocks
    private MessageServiceImpl messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private MessageMapper messageMapper;

    @Mock
    private UsuarioMapper usuarioMapper;

    private UsuarioEntity mockRemetente;
    private UsuarioEntity mockDestinatario;
    private UsuarioEntity mockUsuarioInvalido;

    private MessageEntity mockMessageEntity;
    private MessageInputDTO mockInputDTO;
    private MessageOutputDTO mockOutputDTO;

    @BeforeEach
    void setUp() {
        // remetente AlunoNapne
        mockRemetente = new AlunoNapneEntity();
        mockRemetente.setId(1L);
        mockRemetente.setNome("Aluno Remetente");

        // destinatário Tutor
        mockDestinatario = new TutorEntity();
        mockDestinatario.setId(2L);
        mockDestinatario.setNome("Tutor Destinatário");

        //usuário inválido
        mockUsuarioInvalido = new AlunoEntity();
        mockUsuarioInvalido.setId(3L);

        mockMessageEntity = new MessageEntity();
        mockMessageEntity.setId(100L);
        mockMessageEntity.setUserEnvia(mockRemetente);
        mockMessageEntity.setUserRecebe(mockDestinatario);
        mockMessageEntity.setText("Olá, preciso de ajuda!");
        mockMessageEntity.setVisualizado(false);
        mockMessageEntity.setDataCriacao(LocalDateTime.now());

        mockInputDTO = new MessageInputDTO();
        mockInputDTO.setIdUserEnvia(1L);
        mockInputDTO.setIdUserRecebe(2L);
        mockInputDTO.setText("Olá, preciso de ajuda!");

        mockOutputDTO = new MessageOutputDTO();
        mockOutputDTO.setId(100L);
        mockOutputDTO.setText("Olá, preciso de ajuda!");
    }

    @Test
    @DisplayName("Deve salvar mensagem com sucesso quando usuários são válidos")
    void save_ShouldSaveMessage_WhenUsersAreValid() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockRemetente));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockDestinatario));
        when(messageRepository.save(any(MessageEntity.class))).thenReturn(mockMessageEntity);
        when(messageRepository.findById(100L)).thenReturn(Optional.of(mockMessageEntity));

        MessageOutputDTO result = messageService.save(mockInputDTO);

        assertNotNull(result);
        assertEquals("Olá, preciso de ajuda!", result.getText());
        assertEquals(1L, result.getUserEnvia().getId());
        assertEquals(2L, result.getUserRecebe().getId());

        verify(messageRepository).save(any(MessageEntity.class));
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException no 'save' se tipo de usuário for inválido")
    void save_ShouldThrowException_WhenUserTypeIsInvalid() {

        mockInputDTO.setIdUserEnvia(3L);

        when(usuarioRepository.findById(3L)).thenReturn(Optional.of(mockUsuarioInvalido));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockDestinatario));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            messageService.save(mockInputDTO);
        });

        assertEquals("Um ou mais usuários não têm um tipo válido para envio de mensagens.", exception.getMessage());
        verify(messageRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve encontrar mensagem por ID com sucesso")
    void findById() {

        when(messageRepository.findById(100L)).thenReturn(Optional.of(mockMessageEntity));

        MessageOutputDTO result = messageService.findById(100L);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        verify(messageRepository).findById(100L);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException no 'findById' se mensagem não existir")
    void findById_ShouldThrowNotFound() {

        when(messageRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            messageService.findById(999L);
        });
    }

    @Test
    @DisplayName("Deve converter Entity para OutputDTO corretamente")
    void toMessageOutputDTO() {

        MessageOutputDTO dto = messageService.toMessageOutputDTO(mockMessageEntity);

        assertNotNull(dto);
        assertEquals(mockMessageEntity.getId(), dto.getId());
        assertEquals(mockMessageEntity.getText(), dto.getText());
        assertNotNull(dto.getUserEnvia());
        assertEquals(mockRemetente.getId(), dto.getUserEnvia().getId());
    }

    @Test
    @DisplayName("Deve retornar conversa entre dois usuários")
    void getConversation() {

        List<MessageEntity> conversa = List.of(mockMessageEntity);
        when(messageRepository.getConversation(1L, 2L)).thenReturn(conversa);

        List<MessageOutputDTO> result = messageService.getConversation(1L, 2L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(mockMessageEntity.getText(), result.get(0).getText());
        verify(messageRepository).getConversation(1L, 2L);
    }

    @Test
    @DisplayName("Deve retornar lista de usuários com quem o usuário conversou")
    void getUsersWhoChattedWith() {

        when(messageRepository.getIdsOfUsersWhoChattedWith(1L)).thenReturn(List.of(2L));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockDestinatario));

        List<SimpleUsuarioDTO> result = messageService.getUsersWhoChattedWith(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockDestinatario.getId(), result.get(0).getId());
        assertEquals(mockDestinatario.getNome(), result.get(0).getNome());
    }

    @Test
    @DisplayName("Deve deletar mensagem com sucesso se estiver dentro do limite de 30 minutos")
    void delete_ShouldDeleteMessage_WhenWithinTimeLimit() {

        when(messageRepository.findById(100L)).thenReturn(Optional.of(mockMessageEntity));

        assertDoesNotThrow(() -> {
            messageService.delete(100L);
        });

        verify(messageRepository, times(1)).delete(mockMessageEntity);
    }

    @Test
    @DisplayName("Deve lançar IllegalStateException ao tentar deletar mensagem antiga (> 30 min)")
    void delete_ShouldThrowException_WhenTimeLimitExceeded() {

        mockMessageEntity.setDataCriacao(LocalDateTime.now().minusMinutes(31));
        when(messageRepository.findById(100L)).thenReturn(Optional.of(mockMessageEntity));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            messageService.delete(100L);
        });

        assertEquals("Mensagem só pode ser excluída até 30 minutos após o envio.", exception.getMessage());
        verify(messageRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao tentar deletar mensagem inexistente")
    void delete_ShouldThrowNotFound_WhenMessageDoesNotExist() {

        when(messageRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            messageService.delete(999L);
        });

        verify(messageRepository, never()).delete(any());
    }
    @Test
    @DisplayName("Deve lançar IllegalArgumentException no 'save' se remetente não for encontrado")
    void save_ShouldThrowException_WhenSenderNotFound() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        // when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockDestinatario));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            messageService.save(mockInputDTO);
        });

        assertEquals("Usuário remetente não encontrado", exception.getMessage());
        verify(messageRepository, never()).save(any());
    }
    @Test
    @DisplayName("Deve lançar IllegalArgumentException no 'save' se destinatário não for encontrado")
    void save_ShouldThrowException_WhenReceiverNotFound() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockRemetente));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            messageService.save(mockInputDTO);
        });

        assertEquals("Usuário destinatário não encontrado", exception.getMessage());
        verify(messageRepository, never()).save(any());
    }
    @Test
    @DisplayName("Deve retornar lista vazia no 'getConversation' se não houver mensagens")
    void getConversation_ShouldReturnEmptyList_WhenNoMessages() {

        when(messageRepository.getConversation(1L, 2L)).thenReturn(Collections.emptyList());

        List<MessageOutputDTO> result = messageService.getConversation(1L, 2L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    @DisplayName("Deve lançar ResourceNotFoundException no 'getUsersWhoChattedWith' se usuário listado não existir")
    void getUsersWhoChattedWith_ShouldThrowException_WhenLinkedUserNotFound() {

        when(messageRepository.getIdsOfUsersWhoChattedWith(1L)).thenReturn(List.of(2L));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            messageService.getUsersWhoChattedWith(1L);
        });
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException no 'save' se o destinatário for de tipo inválido")
    void save_ShouldThrowException_WhenReceiverTypeIsInvalid() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockDestinatario));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockUsuarioInvalido));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            messageService.save(mockInputDTO);
        });

        assertEquals("Um ou mais usuários não têm um tipo válido para envio de mensagens.", exception.getMessage());
        verify(messageRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve garantir que nova mensagem é salva como não visualizada")
    void save_ShouldSetVisualizadoToFalse() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockRemetente));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockDestinatario));
        when(messageRepository.save(any(MessageEntity.class))).thenReturn(mockMessageEntity);
        when(messageRepository.findById(100L)).thenReturn(Optional.of(mockMessageEntity));

        messageService.save(mockInputDTO);

        org.mockito.ArgumentCaptor<MessageEntity> messageCaptor = org.mockito.ArgumentCaptor.forClass(MessageEntity.class);
        verify(messageRepository).save(messageCaptor.capture());

        MessageEntity capturedMessage = messageCaptor.getValue();
        assertFalse(capturedMessage.getVisualizado(), "A mensagem deve ser salva como não visualizada (false)");
    }
    @Test
    @DisplayName("Deve mapear todos os detalhes do usuário corretamente no DTO")
    void toMessageOutputDTO_ShouldMapUserDetailsCorrectly() {

        mockRemetente.setBiografia("Bio teste");
        mockRemetente.setMatricula(12345L);

        MessageOutputDTO dto = messageService.toMessageOutputDTO(mockMessageEntity);

        assertNotNull(dto.getUserEnvia());
        assertEquals("Bio teste", dto.getUserEnvia().getBiografia());
        assertEquals(12345L, dto.getUserEnvia().getMatricula());
    }

    @Test
    @DisplayName("Deve salvar mensagem com sucesso quando remetente é Professor")
    void save_ShouldSaveMessage_WhenSenderIsProfessor() {

        UsuarioEntity mockProfessor = new ProfessorEntity();
        mockProfessor.setId(4L);
        mockProfessor.setNome("Professor Teste");

        mockInputDTO.setIdUserEnvia(4L);

        when(usuarioRepository.findById(4L)).thenReturn(Optional.of(mockProfessor));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockDestinatario));
        when(messageRepository.save(any(MessageEntity.class))).thenReturn(mockMessageEntity);
        when(messageRepository.findById(100L)).thenReturn(Optional.of(mockMessageEntity));

        MessageOutputDTO result = messageService.save(mockInputDTO);
        assertNotNull(result);
        verify(messageRepository).save(any(MessageEntity.class));
    }

    @Test
    @DisplayName("Deve retornar lista vazia se usuário nunca conversou com ninguém")
    void getUsersWhoChattedWith_ShouldReturnEmptyList_WhenNoChats() {
        when(messageRepository.getIdsOfUsersWhoChattedWith(1L)).thenReturn(Collections.emptyList());

        List<SimpleUsuarioDTO> result = messageService.getUsersWhoChattedWith(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(usuarioRepository, never()).findById(any());
    }

    @Test
    @DisplayName("Deve salvar mensagem com sucesso quando remetente é Tutor")
    void save_ShouldSaveMessage_WhenSenderIsTutor() {

        UsuarioEntity mockTutorSender = new TutorEntity();
        mockTutorSender.setId(5L);
        mockTutorSender.setNome("Tutor Remetente");

        mockInputDTO.setIdUserEnvia(5L);

        when(usuarioRepository.findById(5L)).thenReturn(Optional.of(mockTutorSender));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockDestinatario));
        when(messageRepository.save(any(MessageEntity.class))).thenReturn(mockMessageEntity);
        when(messageRepository.findById(100L)).thenReturn(Optional.of(mockMessageEntity));

        MessageOutputDTO result = messageService.save(mockInputDTO);

        assertNotNull(result);
        verify(messageRepository).save(any(MessageEntity.class));
    }

}