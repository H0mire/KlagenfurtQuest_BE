package com.muewie.KlagenfurtQuestBackend;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest
public class CheckHTTPResponse {
    @LocalServerPort
    private int port;
}
