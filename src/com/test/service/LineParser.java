package com.test.service;

import com.test.model.QueryLine;
import com.test.model.WaitingTimeLine;

import java.text.ParseException;

public interface LineParser {

    WaitingTimeLine parseWaitingTimeLine(String line) throws ParseException;

    QueryLine parseQueryLine(String line) throws ParseException;
}
