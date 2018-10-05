package com.ml.course.spring.pdp.elastic.repository.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Document(indexName = "blog", type = "article")
public class ArticleDocument {

    @Id
    private String id;

    @MultiField(
            mainField = @Field(type = Text, fielddata = true),
            otherFields = {
                    @InnerField(suffix = "verbatim", type = Keyword)
            })
    private String title;

    @Field(type = Keyword)
    private List<String> tags;

    @Field(type = Nested, includeInParent = true)
    private List<AuthorDocument> authors;

}
