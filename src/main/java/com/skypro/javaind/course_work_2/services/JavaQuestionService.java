package com.skypro.javaind.course_work_2.services;

import com.skypro.javaind.course_work_2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question,answer);
        questions.add(question1);
        return question1;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {

//        return questions.stream().skip(new Random().nextInt(questions.size())).findFirst().orElse(null);

        int item = new Random().nextInt(questions.size());
        int i = 0;
        for (Question question : questions) {
            if(i == item){
                return question;
            }
            i++;
        }
        return null;
    }
}
