package ru.code4fun.test.articles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ArticlesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ArticlesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        ArticleService articleService = new ArticleService();
        List<String> articles = articleService.getTitles(args.length > 0 ? args[0] : null);
        articles.forEach(System.out::println);
    }
}
