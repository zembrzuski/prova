package com.prova.baseb.appb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prova.baseb.appb.entity.Score;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Serializer {

    private final ObjectMapper jsonSerializer = new ObjectMapper();

    public void writeResponse(String serializationType, List<Score> content, HttpServletResponse response) throws IOException {
        if ("csv".equals(serializationType)) {
            response.setContentType("text/plain; charset=utf-8");
            response.getWriter().print(content.stream().map(Score::asCsv).collect(Collectors.joining("\n")));
            return;
        }

        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(jsonSerializer.writeValueAsString(content));
    }

}
