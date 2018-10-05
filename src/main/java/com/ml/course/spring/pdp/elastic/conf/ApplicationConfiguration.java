package com.ml.course.spring.pdp.elastic.conf;

import com.ml.course.spring.pdp.elastic.controller.ArticleController;
import com.ml.course.spring.pdp.elastic.repository.ArticleRepository;
import com.ml.course.spring.pdp.elastic.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private ArticleRepository articleRepository;

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository);
    }

    @Bean
    public ArticleController articleController() {
        return new ArticleController(articleService());
    }

}
