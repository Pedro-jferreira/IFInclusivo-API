package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.MessageInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.service.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

    @Mock
    private MessageService messageService;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @InjectMocks
    private ChatController chatController;

    @Test
    @DisplayName("sendMessage - Deve salvar a mensagem e notificar remetente e destinatário")
    void sendMessage_ShouldSaveAndBroadcast() {

        Long idRemetente = 1L;
        Long idDestinatario = 2L;
        String texto = "Olá, tudo bem?";

        MessageInputDTO inputDTO = new MessageInputDTO();
        inputDTO.setIdUserEnvia(idRemetente);
        inputDTO.setIdUserRecebe(idDestinatario);
        inputDTO.setText(texto);

        MessageOutputDTO outputDTO = new MessageOutputDTO();
        outputDTO.setId(100L);
        outputDTO.setText(texto);

        when(messageService.save(inputDTO)).thenReturn(outputDTO);

        chatController.sendMessage(inputDTO);

        // Verifica se o serviço de salvar foi chamado 1 vez com o DTO correto
        verify(messageService, times(1)).save(inputDTO);

        // Verifica se a mensagem foi enviada para o tópico do DESTINATÁRIO
        verify(simpMessagingTemplate, times(1)).convertAndSend(
                eq("/topic/messages/" + idDestinatario),
                eq(outputDTO)
        );

        // Verifica se a mensagem foi enviada para o tópico do REMETENTE (para feedback visual)
        verify(simpMessagingTemplate, times(1)).convertAndSend(
                eq("/topic/messages/" + idRemetente),
                eq(outputDTO)
        );
    }
    @Test
    @DisplayName("sendMessage - Não deve enviar notificação se o serviço falhar")
    void sendMessage_WhenServiceFails_ShouldNotBroadcast() {

        MessageInputDTO inputDTO = new MessageInputDTO();
        inputDTO.setText("Falha");

        // Simula exceção usuário inválido
        when(messageService.save(inputDTO))
                .thenThrow(new IllegalArgumentException("Erro de validação"));

        assertThrows(IllegalArgumentException.class, () -> chatController.sendMessage(inputDTO));

        verify(simpMessagingTemplate, never()).convertAndSend(anyString(), any(Object.class));
    }
}