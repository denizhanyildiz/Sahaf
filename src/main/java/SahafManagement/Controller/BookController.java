package SahafManagement.Controller;

import SahafManagement.Entity.Book;
import SahafManagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/*
saveBook() veri tabanında kitap yaratmak için POST isteğini işler.
updateBook() veri tabanında bulunan itapların değerlerini güncellemek için PUT isteğini işler
deleteBook() veri tabanında id parametresine göre bulunan kitabı silmek için DELETE isteğini işler..
getBooks() veri tabanında bulunan kitapları kullanıcıya göndermek için GET isteğini işler.
 */


@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @RequestMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(id + " id numaralı kitap veri tabanından silindi..");
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }
}
