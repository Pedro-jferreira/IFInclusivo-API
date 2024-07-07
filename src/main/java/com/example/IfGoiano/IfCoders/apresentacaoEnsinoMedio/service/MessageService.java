package com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.service;

import com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.repository.MessageRepository;
import com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    public MessageEntity createMessage(MessageEntity entity) {
        return messageRepository.save(entity);
    }
    public List<MessageEntity> findAllMessagem() {
        return messageRepository.findAll();
    }
    public MessageEntity getById(Long id) {
        return messageRepository.getReferenceById(id);
    }
    public List<MessageEntity> getExchangedMessages(Long userEnvia, Long userRecebe){
        return messageRepository.getExchangedMessages(userEnvia,userRecebe);
    }
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }



}
