package com.lumanlab.stompapptestserver.controller;

import com.lumanlab.stompapptestserver.websocket.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SimpleWebSocketPubController {

    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/test_channel/1")
    public void message(MessageRequest messageRequest) {
        log.info("요청받은 메시지 -----> {}", messageRequest.getMessage());
        template.convertAndSend("/stomp/message/sub/test_channel/" + 1, messageRequest.getMessage() + "를 보냈습니다.");
    }



}
