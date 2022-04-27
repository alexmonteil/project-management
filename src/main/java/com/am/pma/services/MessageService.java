package com.am.pma.services;

import com.am.pma.dao.IMessageRepository;
import com.am.pma.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    IMessageRepository messageRepository;

    public Message save(Message message) { return messageRepository.save(message); }
    public Message findByMessageId(long messageId) { return messageRepository.findByMessageId(messageId); }
    public void deleteMessage(Message message) { messageRepository.delete(message); }

}
