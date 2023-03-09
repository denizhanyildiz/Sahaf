package SahafManagement.Service;

import SahafManagement.Entity.Bookstore;
import SahafManagement.Repository.IBookstoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
saveBookstore() kitapçıları veri tabanına kaydeder.
deleteBookstore() veri tabanında bulunan kitapçıları siler.
updateBookstore() veri tabanında kitabın olup olmadığını id parametresiyle kontrol eder ve updateBookstore parametresiyle veri tabanında bulunan değerlerini günceller.
getAllBookstores() Veri tabanında bulunan kitapçıları geri döndürür.
 */

@Service
public class BookstoreService {
    IBookstoreRepository iBookstoreRepository;
    public BookstoreService(IBookstoreRepository iBookstoreRepository) {
        this.iBookstoreRepository = iBookstoreRepository;
    }

    public Bookstore saveBookstore(Bookstore bookstore) {
        return iBookstoreRepository.save(bookstore);
    }

    public void deleteBookstore(Long bookstoreId) {
        iBookstoreRepository.deleteById(bookstoreId);
    }

    public Bookstore updateBookstore(Long bookstoreId, Bookstore bookstore) {
        Bookstore bookstore1 = iBookstoreRepository.findById(bookstoreId).orElseThrow(() -> new RuntimeException("Kitapçı bulunamadı"));
        Optional<Bookstore> optionalBookstore = iBookstoreRepository.findById(bookstoreId);
        if (optionalBookstore.isPresent()) {
            Bookstore existingBookstore = optionalBookstore.get();
            existingBookstore.setBookstoreName(bookstore.getBookstoreName());
            existingBookstore.setBookstoreAddress(bookstore.getBookstoreAddress());
            existingBookstore.setBookstoreBooks(bookstore.getBookstoreBooks());
            return iBookstoreRepository.save(existingBookstore);
        } else {
            throw new EntityNotFoundException(bookstoreId + " id numaralı bir sahaf bulunamadı.");
        }
    }

    public List<Bookstore> getAllBookstores() {
        return iBookstoreRepository.findAll();
    }
}
