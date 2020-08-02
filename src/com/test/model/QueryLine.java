package com.test.model;

import com.test.model.comopnent.Question;
import com.test.model.comopnent.Response;
import com.test.model.comopnent.Service;

import java.util.Date;

public class QueryLine extends InputLine{

    private Date responseDateFrom;
    private Date responseDateTo;

    public QueryLine(Service service, Question question, Response responseType) {
        super(service, question, responseType);
    }

    public QueryLine() {}

    public void setResponseDateFrom(Date responseDateFrom) {
        this.responseDateFrom = responseDateFrom;
    }

    public void setResponseDateTo(Date responseDateTo) {
        this.responseDateTo = responseDateTo;
    }

    public boolean isDateInRange(Date date){
        return responseDateFrom.equals(date) ||
                (responseDateTo != null && responseDateFrom.compareTo(date) < 0 && responseDateTo.compareTo(date) > 0);
    }
}
