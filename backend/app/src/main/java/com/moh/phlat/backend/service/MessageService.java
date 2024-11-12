package com.moh.phlat.backend.service;

import java.util.List;

import com.moh.phlat.backend.model.Message;

public interface MessageService {

    Message createMessage(Message message);
    void deleteMessages(List<Message>messages);
}
