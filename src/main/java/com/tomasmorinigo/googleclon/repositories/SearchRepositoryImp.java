package com.tomasmorinigo.googleclon.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tomasmorinigo.googleclon.entities.WebPage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class SearchRepositoryImp implements SearchRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<WebPage> search(String textSearch) {
        String query = "FROM WebPage WHERE description like :textSearch";
        return entityManager.createQuery(query, WebPage.class)
            .setParameter("textSearch", "%"+textSearch+"%")
            .getResultList();
    }

    @Transactional
    @Override
    public void save(WebPage webPage) {
        entityManager.merge(webPage);
        System.out.println("Saved: " + webPage.getUrl());
    }

    @Transactional
    @Override
    public boolean exists(String link) {
        return getByUrl(link) != null;
    }

    @Override
    public WebPage getByUrl(String url) {
        String query = "FROM WebPage WHERE url = :url";
        List<WebPage> listWebs = entityManager.createQuery(query, WebPage.class)
            .setParameter("url", url)
            .getResultList();

        return listWebs.size() == 0 ? null : listWebs.get(0);
    }

    @Override
    public List<WebPage> getLinksToIndex() {
        String query = "FROM WebPage WHERE title is null AND description is null";
        return entityManager.createQuery(query, WebPage.class)
            .setMaxResults(100)
            .getResultList();
    }

    /*
    @Transactional
    @Override
    public void delete(WebPage webPage) {
        entityManager.remove(webPage);
    }
    */
}
