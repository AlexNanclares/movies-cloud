package co.com.taller.showtimeservice.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ShowtimeInDTO {
    private LocalDate date;
    private List<MovieInDTO> movies;
}
