package com.tomasmorinigo.googleclon.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tomasmorinigo.googleclon.entities.WebPage;
import com.tomasmorinigo.googleclon.services.SearchService;

import java.util.Map;

@RestController
public class SearchController {
    
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "api/search", method = RequestMethod.GET)
    public List<WebPage> search(@RequestParam Map<String, String> params){
        // api/search?query=Download Windows&lang=es
        
        String query = params.get("query");
        return searchService.search(query);
    }

}
