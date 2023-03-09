package SahafManagement.Repository;

import SahafManagement.Entity.Bookstore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
JpaRepository interfaceini genişletir ve sahaflar için CRUD işlemlerini uygular
 */

public interface IBookstoreRepository extends JpaRepository<Bookstore, Long> {
    Optional<Bookstore> findById(Long id);
}
