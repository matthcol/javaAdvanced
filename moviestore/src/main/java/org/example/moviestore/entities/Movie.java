package org.example.moviestore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

// lombok
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"id", "title", "year"}) // Warning: exclude associated objects

// JPA
// see doc fetching: https://docs.jboss.org/hibernate/orm/6.3/userguide/html_single/Hibernate_User_Guide.html#fetching
@Entity
@NamedEntityGraph(name = "movie.detail",
        attributeNodes = {
                @NamedAttributeNode("director"),
                @NamedAttributeNode("actors")
        })
@Table(name = "movies")
public class Movie {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(name = "\"year\"", nullable = false)
    private int year;
    private Integer duration;

    @Column(length = 4000)
    private String synopsis;

    @Column(name = "poster_uri")
    private String posterUri;

    @ManyToOne(fetch = FetchType.LAZY) // fetch eager by default
    @JoinColumn(name = "director_id") // NB: default name
    private Person director;

    @ManyToMany // fetch lazy
    @JoinTable(
            name = "play",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Person> actors;
}
