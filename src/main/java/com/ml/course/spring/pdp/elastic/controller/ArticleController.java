package com.ml.course.spring.pdp.elastic.controller;

import com.ml.course.spring.pdp.elastic.service.ArticleService;
import com.ml.course.spring.pdp.elastic.service.model.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(final ArticleService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Article> create(@RequestBody final Article article) {
        return ResponseEntity.ok(service.create(article));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Article> read(@PathVariable(value = "id") final String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Article>> search(@RequestParam(name = "title", required = false) final String title,
                                                @RequestParam(name = "tags", required = false) final String tags) {
        return ResponseEntity.ok(service.search(title, tags));
    }

}
