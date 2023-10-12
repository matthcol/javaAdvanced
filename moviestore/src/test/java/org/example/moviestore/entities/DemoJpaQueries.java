package org.example.moviestore.entities;

import jakarta.persistence.EntityManager;
import org.example.moviestore.repositories.MovieRepository;
import org.example.moviestore.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@ActiveProfiles("queries")
@AutoConfigureTestDatabase(replace = NONE) // do not replace PG DB by H2
public class DemoJpaQueries {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MovieRepository movieRepository;

    @ParameterizedTest
    @ValueSource(ints={0,1})
    void demoFindByIdJpa(int id){
        var person = entityManager.find(Person.class, id);
        System.out.println(person);
    }

    @ParameterizedTest
    @ValueSource(ints={0,1})
    void demoFindByIdSpringJpa(int id){
        var optPerson = personRepository.findById(id);
        System.out.println(optPerson);
        if (optPerson.isPresent()) {
            System.out.println("Person found: " + optPerson.get());
        } else {
            System.out.println("No person found");
        }
    }

    @Test
    void demoMovieDetail(){
        int id = 7131622;
        var movie = movieRepository.findById(id).get();
        System.out.println(movie);
        System.out.println("\t- director: " + movie.getDirector());
        System.out.println("\t- actors: ");
        movie.getActors().stream()
                .forEach(a -> System.out.println("\t\t*" + a));
    }

    @Test
    void demoFilmography1(){
        int directorId = 233;
        var movies = movieRepository.findByDirectorIdOrderByYearDesc(directorId);
        movies.forEach(System.out::println);
    }

    @Test
    void demoFilmography2(){
        int directorId = 233;
        var movies = personRepository.filmography(directorId, Sort.by("year"));
        // movies.forEach(System.out::println);
        movies.forEach(m -> System.out.println(m + " by " + m.getDirector()));
    }

    @ParameterizedTest
    @CsvSource({
            "7131622,1",
            "0,1",
            "7131622,0",
            "0,0"
    })
    void setDirector(int idMovie, int idPerson){
        Optional<Movie> optMovieModified = movieRepository.findById(idMovie)
                .flatMap(m -> personRepository.findById(idPerson)
                        .map(p -> {
                            m.setDirector(p);
                            movieRepository.flush(); // SQL Update
                            return m; // or transform m in a DTO
                        })
                );
        System.out.println(optMovieModified);
    }
}
