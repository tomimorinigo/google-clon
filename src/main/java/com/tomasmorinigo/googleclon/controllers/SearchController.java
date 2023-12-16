package com.tomasmorinigo.googleclon.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tomasmorinigo.googleclon.entities.WebPage;

@RestController
public class SearchController {
    
    @RequestMapping(value = "api/search", method = RequestMethod.GET)
    public List<WebPage> search(){
        List<WebPage> result = new ArrayList<>();
        return result;
    }

}
