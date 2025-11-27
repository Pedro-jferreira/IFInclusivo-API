package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.MessageMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.entity.MessageEntity;
import com.example.IfGoiano.IfCoders.entity.TutorEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
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

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
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

    // Variáveis globais para os testes
    private UsuarioEntity mockRemetente;
    private UsuarioEntity mockDestinatario;
    private MessageEntity mockMessageEntity;
    private MessageInputDTO mockInputDTO;
    private MessageOutputDTO mockOutputDTO;

    @BeforeEach
    void setUp() {

        mockRemetente = new AlunoNapneEntity();
        mockRemetente.setId(1L);
        mockRemetente.setNome("Aluno Remetente");

        mockDestinatario = new TutorEntity();
        mockDestinatario.setId(2L);
        mockDestinatario.setNome("Tutor Destinatário");

        mockMessageEntity = new MessageEntity();
        mockMessageEntity.setId(100L);
        mockMessageEntity.setUserEnvia(mockRemetente);
        mockMessageEntity.setUserRecebe(mockDestinatario);
        mockMessageEntity.setText("Olá, preciso de ajuda!");
        mockMessageEntity.setVisualizado(false);
        mockMessageEntity.setDataCriacao(LocalDateTime.now()); // Criação AGORA (dentro dos 30min)

        mockInputDTO = new MessageInputDTO();
        mockInputDTO.setIdUserEnvia(1L);
        mockInputDTO.setIdUserRecebe(2L);
        mockInputDTO.setText("Olá, preciso de ajuda!");

        mockOutputDTO = new MessageOutputDTO();
        mockOutputDTO.setId(100L);
        mockOutputDTO.setText("Olá, preciso de ajuda!");
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void toMessageOutputDTO() {
    }

    @Test
    void getConversation() {
    }

    @Test
    void getUsersWhoChattedWith() {
    }

    @Test
    void delete() {
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
    @DisplayName("Deve lançar IllegalStateException ao tentar deletar mensagem após 30 minutos")
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
    @DisplayName("Deve salvar mensagem com sucesso quando usuários são válidos")
    void save_ShouldSaveMessage_WhenUsersAreValid() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(mockRemetente));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(mockDestinatario));

        when(messageRepository.save(any(MessageEntity.class))).thenReturn(mockMessageEntity);
        when(messageRepository.findById(100L)).thenReturn(Optional.of(mockMessageEntity));
        MessageOutputDTO result = messageService.save(mockInputDTO);

        assertNotNull(result);
        assertEquals("Olá, preciso de ajuda!", result.getText());
        verify(messageRepository).save(any(MessageEntity.class));
    }
}