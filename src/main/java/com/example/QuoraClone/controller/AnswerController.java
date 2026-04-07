package com.example.QuoraClone.controller;

import com.example.QuoraClone.dtos.AnswerDTO;
import com.example.QuoraClone.models.Answer;
import com.example.QuoraClone.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/answers")
public class AnswerController
{
    private AnswerService answerService;

    public AnswerController(AnswerService answerService)
    {
        this.answerService=answerService;
    }

    @GetMapping("/question/{questionId}")
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId, @RequestParam int page,@RequestParam int size)
    {
        return answerService.getAnswerByQuestionId(questionId,page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id)
    {
        Optional<Answer> answer=answerService.getAnswerById(id);
        return answer.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Answer createAnswer(@RequestBody AnswerDTO answerDTO)
    {
        return answerService.createAnswer(answerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable Long id)
    {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
