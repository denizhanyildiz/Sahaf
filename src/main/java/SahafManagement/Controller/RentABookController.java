package SahafManagement.Controller;

import SahafManagement.Exception.BookNotAvailableException;
import SahafManagement.Exception.BookNotFoundException;
import SahafManagement.Exception.BookstoreNotFoundException;
import SahafManagement.Exception.UserNotFoundException;
import SahafManagement.Service.BookRentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/bookstorerent")
public class RentABookController {

    private BookRentalService bookRentalService;

    public RentABookController(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    @PostMapping("/rent")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> rentBook(@RequestParam Long userId, @RequestParam Long bookstoreId,
                                           @RequestParam Long bookId, @RequestParam LocalDate rentalDate,
                                           @RequestParam LocalDate returnDate) {
        try {
            bookRentalService.rentBook(userId, bookstoreId, bookId, rentalDate, returnDate);
            return ResponseEntity.ok(userId + " id li kullanıcı, " + bookstoreId + " id numaralı sahaftan, " + bookId +" id numaralı kitabı, " + rentalDate + " - " + returnDate + " tarihleri arasında kiralamıştır.");
        } catch (BookstoreNotFoundException | UserNotFoundException | BookNotFoundException | BookNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
