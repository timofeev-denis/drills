package ru.code4fun.test.articles.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("data")
    private List<Article> data;
}
