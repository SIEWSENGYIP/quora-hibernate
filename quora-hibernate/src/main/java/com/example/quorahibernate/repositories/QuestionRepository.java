package com.example.quorahibernate.repositories;

import java.util.List;

import com.example.quorahibernate.entities.Question;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * QuestionRepository
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByUserId(Long id);

    Question findByIdAndUserId(Long id, Long UserId);

    List<Question> findAllByDescriptionContainingIgnoreCase(String description);

}