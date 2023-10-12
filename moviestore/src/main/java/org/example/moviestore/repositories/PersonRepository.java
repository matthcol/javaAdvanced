package org.example.moviestore.repositories;

import org.example.moviestore.entities.Movie;
import org.example.moviestore.entities.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {


    @Query("SELECT m FROM Movie m JOIN fetch m.director d " +
            "WHERE d.id = :idPerson")
    List<Movie> filmography(int idPerson, Sort sort);
}
