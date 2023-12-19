package com.tomasmorinigo.googleclon.repositories;

import java.util.List;

import com.tomasmorinigo.googleclon.entities.WebPage;

public interface SearchRepository {
    
    List<WebPage> search(String textSearch);

    void save(WebPage webPage);

    WebPage getByUrl(String url);

    boolean exists(String link);

    List<WebPage> getLinksToIndex();

    // void delete(WebPage webPage);
}
