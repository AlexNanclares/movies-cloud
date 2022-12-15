package co.com.taller.bookingservice.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShowtimeModel {
    private Long id;
    private LocalDate date;
}
