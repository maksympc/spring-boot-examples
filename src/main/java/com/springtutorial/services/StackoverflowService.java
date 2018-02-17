package com.springtutorial.services;

import com.google.common.collect.ImmutableList;
import com.springtutorial.model.SiteDto;
import com.springtutorial.model.StackOverflowWebsite;
import com.springtutorial.persistence.StackoverflowWebsiteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class StackoverflowService {
    @Autowired
    private StackoverflowWebsiteRepository repository;
    @Autowired
    private StackExchangeClient stackExchangeClient;

//    /**
//     * Method returns all items from repository
//     */
//    public List<StackOverflowWebsite> findAll() {
//        return repository.findAll();
//    }

    /**
     * Method returns all items using rest client
     */
    public List<StackOverflowWebsite> findAll() {
        return stackExchangeClient.getSites().stream()
                .map(this::toStackOverflowWebsite)
                .filter(this::ignoreMeta)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private boolean ignoreMeta(@NonNull StackOverflowWebsite stackOverflowWebsite) {
        return !stackOverflowWebsite.getId().contains(".meta.");
    }


    private StackOverflowWebsite toStackOverflowWebsite(@NonNull SiteDto input) {
        return new StackOverflowWebsite(
                input.getSite_url().substring("https://".length(), input.getSite_url().length() - ".com".length()),
                input.getFavicon_url(),
                input.getSite_url(),
                input.getName(),
                input.getAudience());
    }

}