package com.test.model;

import com.test.model.comopnent.Question;
import com.test.model.comopnent.Response;
import com.test.model.comopnent.Service;

public class InputLine {

    private int index;

    private Service service;
    private Question question;
    private Response responseType;

    public InputLine(Service service, Question question, Response responseType) {
        this.service = service;
        this.question = question;
        this.responseType = responseType;
    }

    public InputLine() {}

    public int getIndex() {
        return index;
    }

    public Service getService() {
        return service;
    }

    public Question getQuestion() {
        return question;
    }

    public Response getResponseType() {
        return responseType;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setResponseType(Response responseType) {
        this.responseType = responseType;
    }
}
