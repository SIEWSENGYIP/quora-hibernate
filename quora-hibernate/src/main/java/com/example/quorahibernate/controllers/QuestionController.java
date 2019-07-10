
package com.example.quorahibernate.controllers;

import java.util.List;

import com.example.quorahibernate.entities.Question;
import com.example.quorahibernate.entities.User;
import com.example.quorahibernate.repositories.QuestionRepository;
import com.example.quorahibernate.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value="/questions")
    public void addQuestion(@RequestBody Question question) {
        User existingUser = userRepository.findById(question.getUserId()).orElse(new User());
        if (existingUser.getId() != null) {
            questionRepository.save(question);
        }
    }


    @GetMapping(value="/questions", produces="application/json")
    public List<Question> displayQuestions(@RequestParam(required=false) String description) {

        if (description != null) {
            return questionRepository.findAllByDescriptionContainingIgnoreCase(description);
        }

        return questionRepository.findAll();
    }
    
}