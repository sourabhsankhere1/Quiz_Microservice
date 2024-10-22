package com.souranj.quiz_service.controller;

import com.souranj.quiz_service.dao.QuizDao;
import com.souranj.quiz_service.model.QuestionWrapper;
import com.souranj.quiz_service.model.QuizDto;
import com.souranj.quiz_service.model.Response;
import com.souranj.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController
{

    @Autowired
    QuizService quizService;

    @PostMapping("create")
 public ResponseEntity<String> CreateQuiz(@RequestBody QuizDto quizdto)
 {
       return quizService.CreatQuiz(quizdto.getCategoryName() ,quizdto.getNumQuestions() ,quizdto.getTitle());

 }

 @GetMapping("get/{id}")
 public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id )
 {
    return quizService.getQuizQuestions(id);

 }

 @PostMapping("submit/{id}")
 public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response>responses)
 {
    return quizService.calculateResult(id,responses);
 }
}
