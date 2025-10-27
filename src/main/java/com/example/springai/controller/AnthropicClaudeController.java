package com.example.springai.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AnthropicClaude chatbot", description = "Created a Chatbot using AnthropicClaude API")
@RestController
@RequestMapping("/api/anthropicClaude")
public class AnthropicClaudeController {
    private ChatClient chatClient;


    public AnthropicClaudeController(AnthropicChatModel chatModel){
        this.chatClient = ChatClient.create(chatModel);
    }
    @Operation(
            summary = "Anthropic Claude chatbot",
            description = "Fetch the Response for the request made"
    )
    @GetMapping("/{request}")
    public ResponseEntity<String> getAnswer(@PathVariable String request){
        String response  = chatClient.prompt(request).call().content();
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
