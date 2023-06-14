package com.skypro.javaind.course_work_2.services;

import com.skypro.javaind.course_work_2.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class MathQuestionServiceTest {
    private MathQuestionService questionService;

    @BeforeEach
    public void setUp() {
        questionService = new MathQuestionService();
    }

    @Test
    public void testAddQuestion() {
        Question question = questionService.add("Сколько будет 2 + 2?", "4");
        Collection<Question> allQuestions = questionService.getAll();

        assertEquals(1, allQuestions.size());
        assertTrue(allQuestions.contains(question));
    }
    @Test
    public void testAddQuestion2() {
        Question question = questionService.add(new Question("Сколько будет 2 + 2?", "4"));
        Collection<Question> allQuestions = questionService.getAll();

        assertEquals(1, allQuestions.size());
        assertTrue(allQuestions.contains(question));
    }

    @Test
    public void testRemoveQuestion() {
        Question question = questionService.add("Сколько будет 2 + 2?", "4");
        questionService.remove(question);
        Collection<Question> allQuestions = questionService.getAll();

        assertEquals(0, allQuestions.size());
        Assertions.assertFalse(allQuestions.contains(question));
    }

    @Test
    public void testGetRandomQuestion() {
        Question question1 = questionService.add("Question 1", "Answer 1");
        Question question2 = questionService.add("Question 2", "Answer 2");
        Question randomQuestion = questionService.getRandomQuestion();

        assertNotNull(randomQuestion);
        assertTrue(randomQuestion.equals(question1) || randomQuestion.equals(question2));
    }
}