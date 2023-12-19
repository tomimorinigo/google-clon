package com.tomasmorinigo.googleclon.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomasmorinigo.googleclon.entities.WebPage;

import static org.hibernate.internal.util.StringHelper.isBlank;

@Service
public class SpiderService {
    
    @Autowired
    private SearchService searchService;

    public void indexWebPages(){
        List<WebPage> linksToIndex = searchService.getLinksToIndex();
        System.out.println(linksToIndex);

        linksToIndex.stream().parallel()
            .forEach(webPage -> {
                try{
                    System.out.println("Indexing: " + webPage.getUrl());
                    indexWebPage(webPage);
                } catch (Exception e){
                    System.err.println(e.getMessage());
                    // searchService.delete(webPage);
                }
            });        
    }

    private void indexWebPage(WebPage webPage) throws Exception{

        String url = webPage.getUrl();
        String content = getWebContent(url);

        if (isBlank(content)) {return;}

        indexAndSaveWebPage(webPage, content);
        System.out.println("Indexed: " + webPage.getUrl());
        saveLinks(content, getDomain(url));
    }

    public void indexAndSaveWebPage(WebPage webPage, String content){

        String title = getTitle(content);
        String description = getDescription(content);
        
        webPage.setTitle(title);
        webPage.setDescription(description);
        searchService.save(webPage);
    }

    public void saveLinks(String content, String domain){
        List<String> links = getLinks(content, domain);

        System.out.println(links); // Borrar

        links.stream()
            .filter(link -> !searchService.exists(link))
            .map(link -> new WebPage(link))
            .forEach(webPage -> searchService.save(webPage));
    }

    public List<String> getLinks(String content, String domain){
        List<String> links = new ArrayList<>();

        String[] splitHref = content.split("href=\"");

        List<String> listHref = Arrays.asList(splitHref);

        listHref
            .stream()
            .forEach(partA -> {
                String[] aux = partA.split("\"");
                links.add(aux[0]);
            });

        
        return cleanLinks(links, domain);
        
    }

    private List<String> cleanLinks(List<String> links, String domain){
       
        String[] excludedStrings = new String[]{
            ".css", ".js", ".json", ".jpg", ".png", ".woff2"
        };

        List<String> linksCleaned = links.stream()
            .distinct() // Ver de borrar
            .filter(link -> Arrays.stream(excludedStrings).noneMatch(link::endsWith))
            .map(link -> link.startsWith("/") ? domain + link : link)
            .filter(link -> link.startsWith("http"))
            .collect(Collectors.toList());

        return linksCleaned;
    }

    private String getDomain(String url) {
        String[] aux = url.split("/");
        return aux[0] + "//" + aux[2];
    }

    public String getTitle(String content){
        String[] aux = content.split("<title>");
        String[] aux2 = aux[1].split("</title>");
        return aux2[0];
    }

    public String getDescription(String content){
        String[] aux = content.split("<meta name=\"description\" content=\"");
        String[] aux2 = aux[1].split("\">");
        return aux2[0];
    }

    private String getWebContent(String link){
        // Download a web page
        
        try {
            URI uri = new URI(link);
            URL url = uri.toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream input = conn.getInputStream(); 
            String body = new BufferedReader(new InputStreamReader(input)) 
                .lines().collect(Collectors.joining());

            return body;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return "";
       
    }

}
