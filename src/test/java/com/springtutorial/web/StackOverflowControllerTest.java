package com.springtutorial.web;

import com.google.common.collect.ImmutableList;
import com.springtutorial.model.StackOverflowWebsite;
import com.springtutorial.persistence.StackoverflowWebsiteRepository;
import com.springtutorial.service.StackoverflowService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StackOverflowControllerTest {
    @Mock
    private StackoverflowService stackoverflowService;
    @InjectMocks
    private StackOverflowController sut;

    @Test
    public void getListOfProviders() {
        //prepare
        when(stackoverflowService.findAll()).thenReturn(ImmutableList.of());
        //testing
        List<StackOverflowWebsite> listOfProviders = sut.getListOfProviders();
        //validate
        verify(stackoverflowService).findAll();
    }
}