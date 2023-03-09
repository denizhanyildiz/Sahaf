package SahafManagement.Repository;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/*
JpaRepository interfaceini genişletir ve kitap kiralamak için CRUD işlemlerini uygular
 */

public interface IBookRentalRepository extends JpaRepository<BookRental, Long> {

    List<BookRental> findBookRentalsByBookAndRentalDateBetween(Book book, LocalDate rentalDate, LocalDate returnDate);
    List<BookRental> findBookRentalsByRentalDate(LocalDate rentalDate);
    List<BookRental> findByBook(Book book);
}
