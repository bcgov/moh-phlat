package com.moh.phlat.backend.service;

import com.moh.phlat.backend.model.Message;
import com.moh.phlat.backend.repository.MessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }
    
    @Override
    public void deleteMessages(List<Message> messages) {
    	messageRepository.deleteAll(messages);
    }
}
