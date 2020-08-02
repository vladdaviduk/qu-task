package com.test.service.impl;

import com.test.model.InputLine;
import com.test.model.QueryLine;
import com.test.model.WaitingTimeLine;
import com.test.model.comopnent.Question;
import com.test.model.comopnent.Response;
import com.test.model.comopnent.Service;
import com.test.service.LineParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.test.validation.utils.Utils.validateLineItemSize;
import static com.test.validation.utils.Utils.validateLineSize;

public class LineParserImpl implements LineParser {

    private final String ALL_MATCHES_MARK = "*";

    @Override
    public WaitingTimeLine parseWaitingTimeLine(String line) throws ParseException {
        String[] lineParts = splitLine(line, 6);

        int DATE_INDEX = 4;
        WaitingTimeLine waitingTimeLine = (WaitingTimeLine) parseGeneralProperties(lineParts, false);
        waitingTimeLine.setResponseDate(parseDate(lineParts[DATE_INDEX]));
        waitingTimeLine.setWaitingTime(parseWaitingTime(lineParts[++DATE_INDEX]));

        return waitingTimeLine;
    }

    @Override
    public QueryLine parseQueryLine(String line) throws ParseException {
        String[] lineParts = splitLine(line, 5);

        int DATE_INDEX = 4;
        String[] dateStr = lineParts[DATE_INDEX].split("-");

        int DATE_TO_INDEX = 1;
        QueryLine queryLine = (QueryLine) parseGeneralProperties(lineParts, true);
        queryLine.setResponseDateFrom(parseDate(dateStr[0]));
        queryLine.setResponseDateTo(dateStr.length > DATE_TO_INDEX ? parseDate(dateStr[DATE_TO_INDEX]) : null);

        return queryLine;
    }

    private InputLine parseGeneralProperties(String[] lineParts, boolean isQuery){
        int index = 0;
        Service service = parseService(lineParts[++index]);
        Question question = parseQuestion(lineParts[++index]);
        Response responseType = parseResponseType(lineParts[++index]);

        return  isQuery ? new QueryLine(service, question, responseType) : new WaitingTimeLine(service, question, responseType);
    }

    private String[] splitLine(String line, int requiredSize) {
        String[] lineParts = line.split(" ");
        validateLineSize(lineParts.length, requiredSize);

        return lineParts;
    }

    private int parseWaitingTime(String waitingTimeStr){
        return Integer.parseInt(waitingTimeStr);
    }

    private Date parseDate(String responseDateStr) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(responseDateStr);
    }

    private Response parseResponseType(String responseStr){

        switch (responseStr){
            case "P": return Response.FIRST;
            case "N": return Response.NEXT;
        }

        throw new RuntimeException("Invalid response type");
    }

    private Service parseService(String serviceStr) {
        String[] serviceParts = splitLineItem(serviceStr, 2);

        Service service = new Service();
        int index = 0;
        if(serviceParts.length > index){
            String serviceId = serviceParts[index];
            if(!serviceId.equals(ALL_MATCHES_MARK)){
                service.setServiceId(Integer.parseInt(serviceId));
                if(serviceParts.length > ++index)
                    service.setVariationId(Integer.parseInt(serviceParts[index]));
            }
        }

        return service;
    }

    private Question parseQuestion(String questionStr) {
        String[] questionParts = splitLineItem(questionStr, 3);

        Question question = new Question();
        int index = 0;
        if(questionParts.length > index){
            String questionId = questionParts[index];
            if(!questionId.equals(ALL_MATCHES_MARK)){
                question.setTypeId(Integer.parseInt(questionId));
                if(questionParts.length > ++index)
                    question.setCategoryId(Integer.parseInt(questionParts[index]));
                if(questionParts.length > ++index)
                    question.setSubcategoryId(Integer.parseInt(questionParts[index]));
            }
        }

        return question;
    }

    private String[] splitLineItem(String lineItem, int maxSize){
        String[] itemParts = lineItem.split("\\.");
        validateLineItemSize(itemParts.length, maxSize);

        return itemParts;
    }
}
