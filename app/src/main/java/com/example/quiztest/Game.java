package com.example.quiztest;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Question> questions ;
    private int numberCorrect;
    private int numberIncorrect;
    private int totalQuestion;
    private int score;
    private Question currentQuestion;

    public Game() {
        numberCorrect = 0;
        numberIncorrect = 0;
        totalQuestion = 0;
        score = 0;
        currentQuestion = new Question(10);
        questions =  new ArrayList<Question>();
    }

    public void makeNewQuestion() {
        currentQuestion = new Question(totalQuestion * 2 + 5);
        totalQuestion++;
        questions.add(currentQuestion);
    }

    public boolean checkAnswer(int submmitedAnswer) {
        boolean isCorrect;
        if (currentQuestion.getAnswer() == submmitedAnswer){
            numberCorrect++;
            isCorrect=true;
        }else {
            numberIncorrect++;
            isCorrect = false;
        }
        score = numberCorrect * 10 - numberIncorrect *30;
        return isCorrect;

    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}

