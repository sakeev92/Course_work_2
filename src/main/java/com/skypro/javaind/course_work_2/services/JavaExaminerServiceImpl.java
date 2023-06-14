package com.skypro.javaind.course_work_2.services;

import com.skypro.javaind.course_work_2.Exceptions.InvalidQuantityQuestionsException;
import com.skypro.javaind.course_work_2.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class JavaExaminerServiceImpl implements ExaminerService {
    public JavaExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    private final QuestionService questionService;
    @Override
    public Collection<Question> getQuestions(int amount) {
        if(amount > questionService.getAll().size() || amount == 0){
            throw new InvalidQuantityQuestionsException("Текущее количество вопросов в коллекции - " + questionService.getAll().size());
        }
        if (amount == questionService.getAll().size()){
            return questionService.getAll();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() != amount){
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
