package com.springtutorial.model;

import lombok.Data;

import java.util.List;

@Data
public class SitesDto {
    private List<SiteDto> items;
}
