package com.lumanlab.stompapptestserver.controller;

import com.lumanlab.stompapptestserver.websocket.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SimpleWebSocketPubController {

    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/test_channel/1")
    public void message(MessageRequest messageRequest) {
        log.info("요청받은 메시지 -----> {}", messageRequest.getMessage());
        template.convertAndSend("/stomp/message/sub/test_channel/" + 1, messageRequest.getMessage() + "를 보냈습니다.");
    }

    @PostMapping("/test_channel/message/send/{id}")
    public ResponseEntity<Void> sendMessage(@PathVariable Long id, @RequestBody MessageRequest messageRequest) {
        log.info("REST API 요청 메시지 -----> {}", messageRequest.getMessage());
        template.convertAndSend("/stomp/message/sub/test_channel/" + id, messageRequest.getMessage() + "를 보냈습니다.");
        return ResponseEntity.ok()
                .build();
    }

}
