package ru.code4fun.test.articles;

import org.springframework.web.client.RestTemplate;
import ru.code4fun.test.articles.model.Article;
import ru.code4fun.test.articles.model.SearchResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class ArticleService {

    private static final String QUERY_BASE_URL = "https://jsonmock.hackerrank.com/api/articles";
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Get all articles titles by <code>author</code> from the server
     *
     * @param author Search this author articles only or search all articles if this argument is null
     * @return Titles
     */
    public List<String> getTitles(String author) {
        List<Article> articles = findArticles(author);

        return articles.stream()
                .map(this::extractArticleTitle)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    /**
     * Get all articles by <code>author</code> from the server.
     */
    private List<Article> findArticles(String author) {
        int pageNumber = 1;
        SearchResult response = sendSearchRequest(author, pageNumber);
        Integer totalPages = response.getTotalPages();
        List<Article> allArticles = new ArrayList<>();
        while (pageNumber <= totalPages) {
            response = sendSearchRequest(author, pageNumber++);
            allArticles.addAll(extractArticles(response));
        }
        return allArticles;
    }

    /**
     * Get articles list from search result
     */
    private List<Article> extractArticles(SearchResult searchResult) {
        return ofNullable(searchResult)
                .map(SearchResult::getData)
                .orElse(Collections.emptyList());
    }

    /**
     * Send search query to the server
     */
    private SearchResult sendSearchRequest(String author, int pageNumber) {
        return restTemplate.getForEntity(getQuery(author, pageNumber), SearchResult.class).getBody();
    }

    /**
     * Make articles search query
     */
    private String getQuery(String author, int pageNumber) {
        return QUERY_BASE_URL + "?page=" + pageNumber + (author == null ? "" : "&author=" + author);
    }

    /**
     * Attempt to determine article title in the following order:
     * <p>1. Value of <code>title</code> field if it is not null and not empty.</p>
     * <p>2. Value of <code>story_title</code> field if it is not null and not empty.</p>
     * <p>3. Null.</p>
     */
    private String extractArticleTitle(Article article) {
        if (isNotEmpty(article.getTitle())) {
            return article.getTitle();
        }
        if (isNotEmpty(article.getStoryTitle())) {
            return article.getStoryTitle();
        }
        return null;
    }

    /**
     * Check if string null or empty
     */
    private boolean isNotEmpty(String string) {
        return string != null && string.length() > 0;
    }
}
