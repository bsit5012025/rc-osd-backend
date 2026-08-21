package org.rocs.osdrmsa.service.chat;

import org.rocs.osdrmsa.dto.request.ChatRequest;
import org.rocs.osdrmsa.dto.response.ChatResponse;

public interface ChatService {

    ChatResponse ask(String username, ChatRequest request);
}
