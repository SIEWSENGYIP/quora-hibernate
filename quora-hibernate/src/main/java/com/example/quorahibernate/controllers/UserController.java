
package com.example.quorahibernate.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.quorahibernate.entities.Answer;
import com.example.quorahibernate.entities.Question;
import com.example.quorahibernate.entities.User;
import com.example.quorahibernate.repositories.AnswerRepository;
import com.example.quorahibernate.repositories.QuestionRepository;
import com.example.quorahibernate.repositories.UserRepository;
import com.example.quorahibernate.response_formats.AnswerJson;
import com.example.quorahibernate.response_formats.QuestionJson;
import com.example.quorahibernate.response_formats.UserSubmissionJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;


    @GetMapping(value="/users", produces="application/json")
    public List<User> displayUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value="/users/{id}/submissions", produces="application/json")
    public UserSubmissionJson displayUserSubmissions(@PathVariable("id") Long id) {
        UserSubmissionJson json = new UserSubmissionJson();
        List<Question> questions = questionRepository.findAllByUserId(id);
        List<Answer> answers = answerRepository.findAllByUserId(id);

        List<QuestionJson> questionJsons = new ArrayList<QuestionJson>();
        List<AnswerJson> answerJsons = new ArrayList<AnswerJson>();

        for (int i = 0; i < questions.size(); i++) {
            questionJsons.add(new QuestionJson(questions.get(i).getDescription()));
        }

        for (int i = 0; i < answers.size(); i++) {
            answerJsons.add(new AnswerJson(answers.get(i).getText()));
        }

        json.setQuestions(questionJsons);
        json.setAnswers(answerJsons);

        return json;
    }

    
    @PostMapping(value="/users")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PostMapping(value="/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable("id") Long id){
        User existingUser = userRepository.findById(id).orElse(new User());
        if (existingUser.getId() != null) {
            user.setId(id);
            userRepository.save(user);
        };   
    }

    @DeleteMapping(value="/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElse(new User());

        if (user.getId() != null) {
            List<Answer> answers = answerRepository.findAllByUserId(id);
            List<Question> questions = questionRepository.findAllByUserId(id);

            answerRepository.deleteAll(answers);
            questionRepository.deleteAll(questions);
            userRepository.delete(user);
        }

    }
    
}