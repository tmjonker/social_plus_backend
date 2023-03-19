package com.tmjonker.socialmediabackend.services;

import com.tmjonker.socialmediabackend.dto.MessageDTO;
import com.tmjonker.socialmediabackend.entities.message.MessageReceived;
import com.tmjonker.socialmediabackend.entities.message.MessageSent;
import com.tmjonker.socialmediabackend.entities.user.User;
import com.tmjonker.socialmediabackend.repositories.MessageReceivedRepository;
import com.tmjonker.socialmediabackend.repositories.MessageSentRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectMessageService {

    private MessageReceivedRepository messageReceivedRepository;
    private MessageSentRepository messageSentRepository;
    private final CustomUserDetailsService userDetailsService;

    public DirectMessageService(MessageReceivedRepository messageReceivedRepository,
                                MessageSentRepository messageSentRepository,
                                CustomUserDetailsService userDetailsService) {

        this.messageReceivedRepository = messageReceivedRepository;
        this.messageSentRepository = messageSentRepository;
        this.userDetailsService = userDetailsService;
    }

    public void processNewMessage(MessageDTO messageDTO) throws UsernameNotFoundException {

        User fromUser = (User) userDetailsService.loadUserByUsername(messageDTO.getFrom());
        User toUser = (User) userDetailsService.loadUserByUsername(messageDTO.getTo());

        MessageReceived messageReceived = new MessageReceived(fromUser.getUsername(), messageDTO.getSubject(), messageDTO.getBody());
        MessageSent messageSent = new MessageSent(toUser.getUsername(), messageDTO.getSubject(), messageDTO.getBody());

        messageSent = messageSentRepository.save(messageSent);
        messageReceived = messageReceivedRepository.save(messageReceived);

        fromUser.addSentMessage(messageSent);
        toUser.addReceivedMessage(messageReceived);

        userDetailsService.saveUser(fromUser);
        userDetailsService.saveUser(toUser);
    }

    public List<MessageReceived> getUserMessagesReceived(String username) throws UsernameNotFoundException {

            User user = userDetailsService.getUserByUsername(username);

            if (user.getReceivedMessages() != null)
                return user.getReceivedMessages();
            else
                return new ArrayList<>();
    }
}
