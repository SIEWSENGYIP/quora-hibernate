package com.example.quorahibernate.response_formats;


/**
 * QuestionJson
 */
public class QuestionJson {
    String description;


    public QuestionJson(String description) {
        this.description = description;
    }
    

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}