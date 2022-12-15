package co.com.taller.bookingservice.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BookingInDTO {
    private Long userid;
    private Long showtimeid;
    private List<MovieInDTO> movies;
}
