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
    
}
