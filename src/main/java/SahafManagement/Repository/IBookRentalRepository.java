package SahafManagement.Repository;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/*
JpaRepository interfaceini genişletir ve kitap kiralamak için CRUD işlemlerini uygular
 */
@Repository
public interface IBookRentalRepository extends JpaRepository<BookRental, Long> {

    List<BookRental> findBookRentalsByBookAndRentalDateBetween(Book book, LocalDate rentalDate, LocalDate returnDate);
    List<BookRental> findBookRentalsByRentalDate(LocalDate rentalDate);

    List<BookRental> findByBook(Book book);
}
