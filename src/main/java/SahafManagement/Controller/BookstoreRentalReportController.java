package SahafManagement.Controller;

import SahafManagement.Entity.BookRental;
import SahafManagement.Service.BookstoreRentalReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookstorereport")
public class BookstoreRentalReportController {
    private BookstoreRentalReportService bookstoreRentalReportService;

    public BookstoreRentalReportController(BookstoreRentalReportService bookstoreRentalReportService) {
        this.bookstoreRentalReportService = bookstoreRentalReportService;
    }

    @GetMapping("/rentals")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> countDailyBookRentalsFromBookstores(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalDate) {
        List<BookRental> dailyRentals = bookstoreRentalReportService.countDailyBookRentalsFromBookstores(rentalDate);
        int rentalCount = dailyRentals.size();
        return ResponseEntity.ok("The total number of books ranted on "+rentalDate.toString() + " is " + rentalCount);
    }
}
