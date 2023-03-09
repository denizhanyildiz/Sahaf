package SahafManagement.Service;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.BookRental;
import SahafManagement.Entity.Bookstore;
import SahafManagement.Repository.IBookRentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
allRentals metoduyla verilen tarih parametresiyle eşleşen bir kiralama olup olmadığını sorgular.
Daha sonra kiralanmak istenen kitapların kirapçılarının olup olmadığının kontrolü yapılır.
Kitabın en az bir kitapçısı varsa kiralanan kitabı günlük kiralanan nesnesine ekler.
Son olarak günlük kiralanan kitap listesi geri döndürülür.
 */

@Service
public class BookstoreRentalReportService {

    private IBookRentalRepository iBookRentalRepository;

    public BookstoreRentalReportService(IBookRentalRepository iBookRentalRepository) {
        this.iBookRentalRepository = iBookRentalRepository;
    }

    public List<BookRental> countDailyBookRentalsFromBookstores(LocalDate rentalDate) {
        List<BookRental> dailyRentals = new ArrayList<>();
        List<BookRental> allRentals = iBookRentalRepository.findBookRentalsByRentalDate(rentalDate);
        for (BookRental rental : allRentals) {
            Book book = rental.getBook();
            List<Bookstore> bookstores = book.getBookBookstores();
            if (bookstores != null && !bookstores.isEmpty()) {
                dailyRentals.add(rental);
            }
        }
        return dailyRentals;
    }
}
