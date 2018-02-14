package com.springtutorial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StackOverflowWebsite {
    @Id
    private String id;
    private String iconImageUrl;
    private String website;
    private String title;
    private String description;
}
