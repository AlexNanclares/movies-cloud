package co.com.taller.showtimeservice.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;
    @Column(name = "id_movie")
    private Long idMovie;
    @Transient
    private String title;
    @Transient
    private String director;
    @Transient
    private int rating;
}
