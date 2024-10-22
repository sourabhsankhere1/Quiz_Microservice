package com.souranj.quiz_service.service;
import com.souranj.quiz_service.dao.QuizDao;
import com.souranj.quiz_service.feign.QuizInterface;
import com.souranj.quiz_service.model.QuestionWrapper;
import com.souranj.quiz_service.model.Quiz;
import com.souranj.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> CreatQuiz(String category, int numQ, String tital)
    {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();

        Quiz quiz=new Quiz();

        quiz.setTitle(tital);
        quiz.setQuestionsIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }

//    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id)
//    {
//       Quiz quiz= quizDao.findById(id).get();
//
//       List<Integer> questionIds=quiz.getQuestionsIds();
//
//       ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
//
//      return questions;


        public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id)
        {
            Quiz quiz = quizDao.findById(id).get();

            List<Integer> questionIds = quiz.getQuestionsIds();

            ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);

            return questions;
        }
    //}

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses)
    {
        ResponseEntity<Integer>score=quizInterface.getScore(responses);



         return score;

    }
}
