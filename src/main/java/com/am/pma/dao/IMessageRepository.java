package com.am.pma.dao;

import com.am.pma.entities.Message;
import org.springframework.data.repository.CrudRepository;


public interface IMessageRepository extends CrudRepository<Message, Long> {
    public Message findByMessageId(long messageId);
}
