package org.example.moviestore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String name;

    // NB:
    // mapping inverse: directedMovies, playedMovie

    private LocalDate birthdate;

    // old temporal types: Date, Calendar
    // @Temporal(TemporalType.DATE)
    // private Date birthdate;
}
