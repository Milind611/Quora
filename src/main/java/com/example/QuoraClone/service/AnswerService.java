package com.example.QuoraClone.service;

import com.example.QuoraClone.dtos.AnswerDTO;
import com.example.QuoraClone.models.Answer;
import com.example.QuoraClone.models.Question;
import com.example.QuoraClone.repositories.AnswerRepository;
import com.example.QuoraClone.repositories.QuestionRepository;
import com.example.QuoraClone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService
{
    private AnswerRepository answerRepository;
    private UserRepository userRepository;
    private QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository,UserRepository userRepository,QuestionRepository questionRepository)
    {
        this.answerRepository=answerRepository;
        this.userRepository=userRepository;
        this.questionRepository=questionRepository;
    }

    public List<Answer> getAnswerByQuestionId(Long questionId,int page,int size)
    {
        return answerRepository.findByQuestionId(questionId, PageRequest.of(page,size)).getContent();
    }

    public Optional<Answer> getAnswerById(Long id)
    {
        return answerRepository.findById(id);
    }

    public Answer createAnswer(AnswerDTO answerDTO)
    {
        Answer answer=new Answer();
        answer.setContent(answerDTO.getContent());

        Optional<Question> question=questionRepository.findById(answerDTO.getQuestionId());
        question.ifPresent(answer::setQuestion);

        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id)
    {
        answerRepository.deleteById(id);
    }


}
