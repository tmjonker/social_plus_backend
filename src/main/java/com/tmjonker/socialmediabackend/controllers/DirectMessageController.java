package com.tmjonker.socialmediabackend.controllers;

import com.tmjonker.socialmediabackend.dto.MessageDTO;
import com.tmjonker.socialmediabackend.entities.message.MessageReceived;
import com.tmjonker.socialmediabackend.services.DirectMessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DirectMessageController {

    private DirectMessageService directMessageService;

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


        List<MessageReceived> messagesReceived = directMessageService.getUserMessagesReceived(username);

        if (messagesReceived != null) {
            return new ResponseEntity<>(messagesReceived, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
