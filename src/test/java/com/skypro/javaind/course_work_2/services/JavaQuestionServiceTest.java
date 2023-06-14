package com.skypro.javaind.course_work_2.services;
import com.skypro.javaind.course_work_2.model.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

    private JavaQuestionService questionService;

    @BeforeEach
    public void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    public void testAddQuestion() {
        Question question = questionService.add("Какой тип переменных исползуется для символов в Java", "char");
        Collection<Question> allQuestions = questionService.getAll();

        assertEquals(1, allQuestions.size());
        assertTrue(allQuestions.contains(question));
    }
    @Test
    public void testAddQuestion2() {
        Question question = questionService.add(new Question("Какой тип переменных исползуется для символов в Java", "char"));
        Collection<Question> allQuestions = questionService.getAll();

        assertEquals(1, allQuestions.size());
        assertTrue(allQuestions.contains(question));
    }

    @Test
    public void testRemoveQuestion() {
        Question question = questionService.add("Какой тип переменных исползуется для символов в Java", "char");
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
