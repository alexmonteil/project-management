package com.am.pma.services;

import com.am.pma.dao.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    IMessageRepository messageRepository;
}
