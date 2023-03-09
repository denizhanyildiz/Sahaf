package SahafManagement.Repository;

import SahafManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
JpaRepository interfaceini genişletir ve kullanıcılar için CRUD işlemlerini uygular
 */
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}
