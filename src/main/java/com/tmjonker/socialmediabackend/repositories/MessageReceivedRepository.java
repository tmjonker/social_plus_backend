package com.tmjonker.socialmediabackend.repositories;

import com.tmjonker.socialmediabackend.entities.message.MessageReceived;
import org.springframework.data.repository.CrudRepository;

public interface MessageReceivedRepository extends CrudRepository<MessageReceived, Long> {
}
