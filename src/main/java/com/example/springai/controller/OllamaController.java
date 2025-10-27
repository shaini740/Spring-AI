package com.example.springai.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ollama chatbot", description = "Created a Chatbot using Ollama AI API")
@RestController
@RequestMapping("/api/ollama")
public class OllamaController {

    private ChatClient chatClient;
     public OllamaController(OllamaChatModel chatModel){
         this.chatClient = ChatClient.create(chatModel);
     }

    @Operation(
            summary = "Ollama chatbot",
            description = "Fetch the Response for the request made"
    )
    @GetMapping("/{request}")
    public ResponseEntity<String> getAnswer(@PathVariable String request){
        ChatResponse chatResponse = chatClient
                .prompt(request)
                .call()
                .chatResponse();
        String response  = chatResponse.getResult()
                .getOutput()
                .getText();
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
