package com.ml.course.spring.pdp.elastic.repository;

import com.ml.course.spring.pdp.elastic.repository.model.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ArticleRepository extends ElasticsearchRepository<ArticleDocument, String> {
}
