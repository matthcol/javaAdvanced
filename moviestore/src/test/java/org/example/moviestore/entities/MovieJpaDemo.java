package org.example.moviestore.entities;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA Hibernate + H2
class MovieJpaDemo {

    @Autowired
    EntityManager entityManager;

    @Test // NB: inside a transaction with of rollback at the end
    void demoSave(){
        var movie = Movie.builder()
                .title("Un Talent en Or Massif")
                .year(2022)
                .build();
        entityManager.persist(movie);
        entityManager.flush(); // SQL: INSERT (with strategy sequence)
    }

    @Test
    @Rollback(false) // commit at the end
    void demoSaveCommit(){
        var movie = Movie.builder()
                .title("Un Talent en Or Massif")
                .year(2022)
                .build();
        entityManager.persist(movie);
    } // SQL: INSERT

}