package com.example.springai.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Open AI API chatbot", description = "Created a Chatbot using Open AI API")
@RestController
@RequestMapping("/api/openai")
public class OpenAiController {

    private OpenAiChatModel openAiChatModel;

    public OpenAiController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    @Operation(
            summary = "Open AI API chatbot",
            description = "Fetch the Response for the request made"
    )
    @GetMapping("/{request}")
    public ResponseEntity<String> getAnswer(@PathVariable String request){
        String response  = openAiChatModel.call(request);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
