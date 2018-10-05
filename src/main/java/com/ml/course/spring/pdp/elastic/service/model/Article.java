package com.ml.course.spring.pdp.elastic.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "title", "tags", "authors"})
@Data
public class Article {

    @JsonProperty
    private String id;

    @JsonProperty
    private String title;

    @JsonProperty
    private List<String> tags;

    @JsonProperty
    private List<Author> authors;

}
