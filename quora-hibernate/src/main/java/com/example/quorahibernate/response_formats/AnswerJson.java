package com.example.quorahibernate.response_formats;


/**
 * AnswerJson
 */

public class AnswerJson {
    String text;


    public AnswerJson(String text) {
        this.text = text;
    }


    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}