package co.com.taller.showtimeservice.model;

import lombok.Data;

@Data
public class MovieModel {
    private Long id;
    private String title;
    private String director;
    private int rating;
}
