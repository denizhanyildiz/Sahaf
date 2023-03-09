package SahafManagement.Repository;

import SahafManagement.Entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/*
JpaRepository interfaceini genişletir ve loglar için CRUD işlemlerini uygular
 */

public interface ILogRepository extends JpaRepository<Log, Long> {
}
