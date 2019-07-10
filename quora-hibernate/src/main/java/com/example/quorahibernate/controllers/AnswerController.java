
package com.example.quorahibernate.controllers;

import java.util.List;

import com.example.quorahibernate.entities.Answer;
import com.example.quorahibernate.entities.Question;
import com.example.quorahibernate.repositories.AnswerRepository;
import com.example.quorahibernate.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @PostMapping(value="/questions/{id}/answers")
    public void addAnswer(@RequestBody Answer answer, @PathVariable("id") Long id) {
        Question question = questionRepository.findByIdAndUserId(id, answer.getUserId());
        if (question != null && question.getId() != null) {
            answer.setQuestionId(id);
            answerRepository.save(answer);
        }
    }

    @PostMapping(value="/answers/{id}")
    public void updateAnswer(@RequestBody Answer answer, @PathVariable("id") Long id){
        String text = answer.getText();
        Answer existingAnswer = answerRepository.findById(id).orElse(new Answer());
        if (existingAnswer.getId() != null) {
            answer = existingAnswer;
            answer.setText(text);
            answerRepository.save(answer);
        };   
    }

    @GetMapping(value="/questions/{id}/answers", produces="application/json")
    public List<Answer> displayAnswers(@PathVariable("id") Long id) {

        return answerRepository.findAllByQuestionId(id);
    }
    
}