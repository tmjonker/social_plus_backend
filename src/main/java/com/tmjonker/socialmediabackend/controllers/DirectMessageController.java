package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.dto.MessageDTO;
import com.tmjonker.socialmediabackend.entities.message.MessageReceived;
import com.tmjonker.socialmediabackend.entities.message.MessageSent;
import com.tmjonker.socialmediabackend.services.DirectMessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DirectMessageController {

    private final  DirectMessageService directMessageService;

    public DirectMessageController(DirectMessageService directMessageService) {

        this.directMessageService = directMessageService;
    }

    @PostMapping("/direct-message")
    public ResponseEntity<?> postDirectMessage(@RequestBody MessageDTO messageDTO) {

        try {
            directMessageService.processNewMessage(messageDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/direct-message/{username}")
    public ResponseEntity<?> getUserReceivedMessages(@PathVariable String username) {

        try {
            List<MessageReceived> messagesReceived = directMessageService.getUserMessagesReceived(username);
            return new ResponseEntity<>(messagesReceived, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/direct-message/read")
    public ResponseEntity<?> postReadMessages(@RequestBody List<MessageReceived> messageReceivedList) {
        try {
            List<MessageReceived> messagesReceived = directMessageService.processReadMessages(messageReceivedList);
            return new ResponseEntity<>(messagesReceived, HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/direct-message/sent/{username}")
    public ResponseEntity<?> getUserSentMessages(@PathVariable String username) {

        try {
            List<MessageSent> messagesSent = directMessageService.getUserMessagesSent(username);
            return new ResponseEntity<>(messagesSent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
