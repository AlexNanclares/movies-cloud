package co.com.taller.bookingservice.model;

import lombok.Data;

@Data
public class MovieModel {
    private Long id;
    private String title;
    private String director;
    private int rating;
}
