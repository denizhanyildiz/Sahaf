package SahafManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Veri tabanı sınıfı fieldlerin her biri bir sütuna denktir.
Manytomany anotasyonu ile bir kiralamanın birden çok kullanıcı ile etkileşime girebileceğinin tanımlarız.
 */

import java.time.LocalDate;

@Entity
@Table(name = "bookrentals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookRentalId;
    @Column(name = "rental_date")
    @Temporal(TemporalType.DATE)
    private LocalDate rentalDate;
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private LocalDate returnDate;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
