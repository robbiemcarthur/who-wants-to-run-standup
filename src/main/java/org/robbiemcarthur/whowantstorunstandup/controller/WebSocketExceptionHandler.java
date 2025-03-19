package org.robbiemcarthur.whowantstorunstandup.controller;

import org.robbiemcarthur.whowantstorunstandup.model.websocket.response.WebSocketResponse;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class WebSocketExceptionHandler {

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public WebSocketResponse<String> handleException(Exception ex) {
        return new WebSocketResponse<>("ERROR", ex.getMessage(), null);
    }
}

