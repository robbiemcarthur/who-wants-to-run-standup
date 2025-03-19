package org.robbiemcarthur.whowantstorunstandup.model.websocket.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WebSocketResponse<T> {
    private String status;
    private String message;
    private T data;
}

