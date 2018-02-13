package com.springtutorial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StackOverflowWebsite {
    @Id
    private final String id;
    private final String iconImageUrl;
    private final String website;
    private final String title;
    private final String description;

    public StackOverflowWebsite(String id, String iconImageUrl, String website, String title, String description) {
        this.id = id;
        this.iconImageUrl = iconImageUrl;
        this.website = website;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getIconImageUrl() {
        return iconImageUrl;
    }

    public String getWebsite() {
        return website;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
