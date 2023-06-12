package com.skypro.javaind.course_work_2.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.skypro.javaind.course_work_2.Exceptions.InvalidQuantityQuestionsException;
import com.skypro.javaind.course_work_2.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collection;
import java.util.Set;

public class JavaExaminerServiceImplTest {

    private JavaExaminerServiceImpl examinerService;

    @Mock
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        examinerService = new JavaExaminerServiceImpl(questionService);
    }

    @Test
    public void testGetQuestions_Success() {
        Question question1 = new Question("Question 1", "Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");
        Question question3 = new Question("Question 3", "Answer 3");

        Set<Question> allQuestions = Set.of(question1, question2, question3);

        when(questionService.getAll()).thenReturn(allQuestions);
        when(questionService.getRandomQuestion()).thenReturn(question1, question2, question3);

        Collection<Question> result = examinerService.getQuestions(3);
        assertEquals(3, result.size());
        assertTrue(result.contains(question1));
        assertTrue(result.contains(question2));
        assertTrue(result.contains(question3));

        verify(questionService, never()).getRandomQuestion();

        result = examinerService.getQuestions(2);
        assertEquals(2, result.size());
        verify(questionService, times(2)).getRandomQuestion();

        verify(questionService, times(5)).getAll();

    }

    @Test
    public void testGetQuestions_InvalidQuantity() {
        Question question1 = new Question("Question 1", "Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");
        Set<Question> allQuestions = Set.of(question1, question2);

        when(questionService.getAll()).thenReturn(allQuestions);

        assertThrows(InvalidQuantityQuestionsException.class, () -> {
            examinerService.getQuestions(3);
        });

        verify(questionService, times(2)).getAll();
        verify(questionService, never()).getRandomQuestion();
    }
}
