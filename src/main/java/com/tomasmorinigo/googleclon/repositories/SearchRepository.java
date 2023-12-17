package com.tomasmorinigo.googleclon.repositories;

import java.util.List;

import com.tomasmorinigo.googleclon.entities.WebPage;

public interface SearchRepository {
    
    public List<WebPage> search(String textSearch);

}
