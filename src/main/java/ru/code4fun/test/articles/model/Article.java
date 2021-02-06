package ru.code4fun.test.articles.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Article {

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("author")
    private String author;

    @JsonProperty("num_comments")
    private Integer numComments;

    @JsonProperty("story_id")
    private String storyId;

    @JsonProperty("story_title")
    private String storyTitle;

    @JsonProperty("story_url")
    private String storyUrl;

    @JsonProperty("parent_id")
    private String parentId;

    @JsonProperty("created_at")
    private String createdAt;
}