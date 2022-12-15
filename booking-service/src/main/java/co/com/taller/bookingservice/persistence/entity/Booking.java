package co.com.taller.bookingservice.persistence.entity;

import co.com.taller.bookingservice.model.ShowtimeModel;
import co.com.taller.bookingservice.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;
    @NotNull(message = "El Id del usuario no puede ser nulo")
    @Column(name = "user_id")
    private Long userid;
    @Transient
    private UserModel userModel;
    @NotNull(message = "El Id de la programación no puede ser nula")
    @Column(name = "showtime_id")
    private Long showtimeid;
    @Transient
    private ShowtimeModel showtimeModel;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Movie> movies;

}
