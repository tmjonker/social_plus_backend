package com.tmjonker.socialmediabackend.repositories;

import com.tmjonker.socialmediabackend.entities.message.MessageSent;
import org.springframework.data.repository.CrudRepository;

public interface MessageSentRepository extends CrudRepository<MessageSent, Long> {
}
