package SahafManagement.Controller;

import SahafManagement.Entity.Bookstore;
import SahafManagement.Service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController {
    private BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @PostMapping
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Bookstore> createBookstore(@RequestBody Bookstore bookstore) {
        Bookstore savedBookstore = bookstoreService.saveBookstore(bookstore);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookstore);
    }

    @DeleteMapping("/delete/{bookstoreId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteBookstore(@PathVariable Long bookstoreId) {
        System.out.println(bookstoreId + " id numaralı sahaf veri tabanından silindi");
        bookstoreService.deleteBookstore(bookstoreId);
        return ResponseEntity.ok(bookstoreId + " id numaralı sahaf veri tabanından silindi.");
    }

    @PostMapping("/update/{bookstoreId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Bookstore updateBookstore(@PathVariable Long bookstoreId, @RequestBody Bookstore bookstore) {
        return bookstoreService.updateBookstore(bookstoreId, bookstore);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Bookstore> getAllBookstores() {
        return bookstoreService.getAllBookstores();
    }
}
