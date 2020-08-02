package com.test;

import com.test.model.InputLine;
import com.test.model.QueryLine;
import com.test.model.WaitingTimeLine;
import com.test.service.LineParser;
import com.test.service.impl.LineParserImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static com.test.validation.utils.Utils.handleInputException;
import static com.test.validation.utils.Utils.validateInputSize;

public class Main {

    private static final LineParser lineParser = new LineParserImpl();

    public static void main(String[] args) throws FileNotFoundException {

        final String WAITING_TIME_LINE = "C", QUERY_LINE = "D";

        List<String> lines = readInputFile();

        validateInputSize(!lines.isEmpty() ? Integer.parseInt(lines.get(0)) : 0);

        List<WaitingTimeLine> waitingTimeLines = lines.stream()
                .filter(line -> line.startsWith(WAITING_TIME_LINE))
                .map(line -> (WaitingTimeLine) parseInputLine(line, lines.indexOf(line), false))
                .collect(Collectors.toList());

        lines.stream().filter(line -> line.startsWith(QUERY_LINE))
                .map(line -> (QueryLine) parseInputLine(line, lines.indexOf(line), true))
                .forEach(query -> {
                    int averageWaitingTime = (int) waitingTimeLines.stream()
                            .filter(timeLine -> timeLine.isMatchToQuery(query))
                            .mapToInt(WaitingTimeLine::getWaitingTime)
                            .average().orElse(-1);

                    System.out.println(averageWaitingTime > 0 ? averageWaitingTime : "-");
                });
    }

    private static InputLine parseInputLine(String line, int lineIndex, boolean isQuery){
        try {
            InputLine inputLine = isQuery ? lineParser.parseQueryLine(line) : lineParser.parseWaitingTimeLine(line);
            inputLine.setIndex(lineIndex);
            return inputLine;
        } catch (Exception e) {
            handleInputException(e, lineIndex + 1);
        }
        return null;
    }

    private static List<String> readInputFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("input.txt")).getFile()));

        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine())
            lines.add(sc.nextLine());

        return lines;
    }
}
