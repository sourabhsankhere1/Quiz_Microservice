package com.souranj.quiz_service.dao;

import com.souranj.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer>
{

}
