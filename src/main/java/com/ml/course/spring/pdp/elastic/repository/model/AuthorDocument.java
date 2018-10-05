package com.ml.course.spring.pdp.elastic.repository.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Data
public class AuthorDocument {

    @Field(type = Text)
    private String name;

}
