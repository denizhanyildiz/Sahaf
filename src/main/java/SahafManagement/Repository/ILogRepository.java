package SahafManagement.Repository;

import SahafManagement.Entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
JpaRepository interfaceini genişletir ve loglar için CRUD işlemlerini uygular
 */
@Repository
public interface ILogRepository extends JpaRepository<Log, Long> {
}
