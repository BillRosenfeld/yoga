package bill.rosenfeld.yoga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AsanaDao extends JpaRepository<Asana, Long>, JpaSpecificationExecutor<Asana> {

}
