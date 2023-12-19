package com.tomasmorinigo.googleclon.services;

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

    public void save(WebPage webPage){
        repository.save(webPage);
    }

    /*
    public void delete(WebPage webPage){
        repository.delete(webPage);
    }*/

    public boolean exists(String link) {
        return repository.exists(link);
    }

    public List<WebPage> getLinksToIndex(){
        return repository.getLinksToIndex();
    }
}
