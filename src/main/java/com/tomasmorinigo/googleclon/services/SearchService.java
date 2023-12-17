package com.tomasmorinigo.googleclon.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomasmorinigo.googleclon.entities.WebPage;
import com.tomasmorinigo.googleclon.repositories.SearchRepository;

@Service
public class SearchService {
    
    @Autowired
    private SearchRepository repository;

    public List<WebPage> search(String textSearch){
        return repository.search(textSearch);
    }
}
