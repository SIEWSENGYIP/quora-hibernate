package com.example.quorahibernate.repositories;

import java.util.List;

import com.example.quorahibernate.entities.Answer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AnswerRepository
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByUserId(Long id);

    List<Answer> findAllByQuestionId(Long id);
    
}