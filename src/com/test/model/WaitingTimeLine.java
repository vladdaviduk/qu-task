package com.test.model;

import com.test.model.comopnent.Question;
import com.test.model.comopnent.Response;
import com.test.model.comopnent.Service;

import java.util.Date;

public class WaitingTimeLine extends InputLine {

    private Date responseDate;
    private int waitingTime;

    public WaitingTimeLine(Service service, Question question, Response responseType) {
        super(service, question, responseType);
    }

    public WaitingTimeLine() {}

    public Date getResponseDate() {
        return responseDate;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public boolean isMatchToQuery(QueryLine queryLine){
        return queryLine.getIndex() > this.getIndex() &&
                queryLine.getService().isMatch(this.getService()) &&
                queryLine.getQuestion().isMatch(this.getQuestion()) &&
                queryLine.getResponseType() == this.getResponseType() &&
                queryLine.isDateInRange(this.getResponseDate());
    }
}
