package com.tomasmorinigo.googleclon.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name = "web_pages")
@Getter @Setter @ToString @EqualsAndHashCode
public class WebPage {
    
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
    
    public WebPage(){}

    public WebPage(String url){
        this.url = url;
    }
}
