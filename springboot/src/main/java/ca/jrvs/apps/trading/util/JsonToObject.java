package ca.jrvs.apps.trading.util;

import java.util.List;
import ca.jrvs.apps.trading.model.IexQuote;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.criteria.Root;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class JsonToObject {


    public List<IexQuote> jsonToQuote(String jsonString) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        IexQuote quote = objectMapper.readValue(jsonString, IexQuote.class);

        ObjectWriter writer = objectMapper.writer();
        writer = writer.with(SerializationFeature.INDENT_OUTPUT);

        List<IexQuote> quotes = objectMapper.readValue(jsonString, new TypeReference<List<IexQuote>>(){});


        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(quotes);

        System.out.println(prettyJson);
        return quotes;
    }



}