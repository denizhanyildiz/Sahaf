package SahafManagement.Repository;

import SahafManagement.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
JpaRepository interfaceini genişletir ve Kitap için CRUD işlemlerini uygular
 */

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
