package com.example.quorahibernate.response_formats;

import java.util.List;


/**
 * UserSubmissionJson
 */
public class UserSubmissionJson {
    List<QuestionJson> questions;
    List<AnswerJson> answers;

    public List<QuestionJson> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<QuestionJson> questions) {
        this.questions = questions;
    }

    public List<AnswerJson> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<AnswerJson> answers) {
        this.answers = answers;
    }

}