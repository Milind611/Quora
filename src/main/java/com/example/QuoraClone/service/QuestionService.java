package com.example.QuoraClone.service;

import com.example.QuoraClone.dtos.QuestionDTO;
import com.example.QuoraClone.models.Question;
import com.example.QuoraClone.models.Tag;
import com.example.QuoraClone.models.User;
import com.example.QuoraClone.repositories.QuestionRepository;
import com.example.QuoraClone.repositories.TagRepository;
import com.example.QuoraClone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService
{
    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository,UserRepository userRepository,TagRepository tagRepository)
    {
        this.questionRepository=questionRepository;
        this.tagRepository=tagRepository;
        this.userRepository=userRepository;
    }

    public void deleteQuestion(Long id)
    {
        questionRepository.deleteById(id);
    }

    public List<Question> getQuestions(int offset,int limit)
    {
        return questionRepository.findAll(PageRequest.of(offset,limit)).getContent();
    }

    public Optional<Question> getQuestionById(Long id)
    {
        return questionRepository.findById(id);
    }

    public Question createQuestion(QuestionDTO questionDTO)
    {
        Question question=new Question();
        question.setContent(questionDTO.getContent());
        question.setTitle(questionDTO.getTitle());

        Optional<User> user =userRepository.findById(questionDTO.getUserId());
        user.ifPresent(question::setUser);

        Set<Tag> tags=questionDTO.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(java.util.stream.Collectors.toSet());
        question.setTags(tags);
        return questionRepository.save(question);

    }
}
