package SahafManagement.Repository;

import SahafManagement.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/*
JpaRepository interfaceini genişletir ve Kitap için CRUD işlemlerini uygular
 */


public interface IBookRepository extends JpaRepository<Book, Long> {
}
