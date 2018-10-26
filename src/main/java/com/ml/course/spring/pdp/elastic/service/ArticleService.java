package com.ml.course.spring.pdp.elastic.service;

import com.ml.course.spring.pdp.elastic.repository.ArticleRepository;
import com.ml.course.spring.pdp.elastic.repository.model.ArticleDocument;
import com.ml.course.spring.pdp.elastic.repository.model.AuthorDocument;
import com.ml.course.spring.pdp.elastic.service.model.Article;
import com.ml.course.spring.pdp.elastic.service.model.Author;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(final ArticleRepository repository) {
        this.repository = repository;
    }

    public Article create(final Article article) {
        final ArticleDocument document = convert(article);
        //repository.save(document);

        return convert(document);
    }

    public Article get(final String id) {
//        final Optional<ArticleDocument> document = repository.findById(id);
//        if (!document.isPresent()) {
//            throw new ObjectNotFoundException(String.format("Article with id '%s' does not exist", id));
//        }
//        return convert(document.get());

        final ArticleDocument document = new ArticleDocument();
        document.setId(id);
        document.setTitle(String.format("Test title: %s", id));

        return convert(document);
    }

    public List<Article> search(final String title, final String tags) {
        final BoolQueryBuilder boolQuery = boolQuery();
        if (!StringUtils.isEmpty(title)) {
            boolQuery.must(matchQuery("title", title));
        }

        if (!StringUtils.isEmpty(tags)) {
            boolQuery.must(termQuery("tags", tags));
        }

        final SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .withPageable(Pageable.unpaged())
                .build();

//        final Page<ArticleDocument> documents = repository.search(query);
//        return documents.stream()
//                .map(this::convert)
//                .collect(Collectors.toList());

        return Collections.EMPTY_LIST;
    }

    private Article convert(final ArticleDocument document) {
        final Article article = new Article();
        article.setId(document.getId());
        article.setTitle(document.getTitle());
        article.setTags(document.getTags());

        if (!CollectionUtils.isEmpty(document.getAuthors())) {
            article.setAuthors(document.getAuthors().stream()
                    .map(authorDocument -> {
                        final Author author = new Author();
                        author.setName(authorDocument.getName());

                        return author;
                    }).collect(Collectors.toList()));
        }

//        QueryBuilder boolQueryBuilder = boolQuery().should(matchQuery("skuCode", keyword)).should(matchQuery("name", keyword));
//        FilterBuilder filterBuilder = boolFilter().must(termFilter("enabled", true), termFilter("type", "SIMPLE"), termFilter("tenantCode", "Triveni"));
//        NativeSearchQueryBuilder().withQuery(QueryBuilders.filteredQuery(boolQueryBuilder, filterBuilder).build();

        return article;
    }

    private ArticleDocument convert(final Article article) {
        final ArticleDocument document = new ArticleDocument();
        document.setTitle(article.getTitle());
        document.setTags(article.getTags());
        if (!CollectionUtils.isEmpty(article.getAuthors())) {
            document.setAuthors(article.getAuthors().stream()
                    .map(author -> {
                        final AuthorDocument authorDocument = new AuthorDocument();
                        authorDocument.setName(author.getName());
                        return authorDocument;
                    }).collect(Collectors.toList()));
        }

        return document;
    }
}
