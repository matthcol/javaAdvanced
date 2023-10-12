package org.example.moviestore.repositories;

import org.example.moviestore.entities.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    // see doc fetching: https://docs.jboss.org/hibernate/orm/6.3/userguide/html_single/Hibernate_User_Guide.html#fetching
    @Override
    @EntityGraph("movie.detail")
    Optional<Movie> findById(Integer id);

    List<Movie> findByDirectorIdOrderByYearDesc(Integer personId);
    List<Movie> findByDirectorIdOrderByYear(Integer personId, Sort sort);
}
