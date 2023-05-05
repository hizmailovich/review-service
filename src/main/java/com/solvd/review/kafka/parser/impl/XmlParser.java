package com.solvd.review.kafka.parser.impl;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;

import com.solvd.review.kafka.parser.Parser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class XmlParser implements Parser {

    @Override
    @SneakyThrows
    public String getValue(final String filename,
                           final String node) {
        XML xml = new XMLDocument(Objects.requireNonNull(
                XmlParser.class
                        .getClassLoader()
                        .getResource(filename)));
        return xml.nodes("//" + node)
                .get(0)
                .xpath("text()")
                .get(0);
    }

}
