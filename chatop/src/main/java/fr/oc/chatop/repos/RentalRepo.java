package fr.oc.chatop.repos;
import fr.oc.chatop.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface RentalRepo  extends JpaRepository<Rental, Long> {

    Optional<Rental> findByName(String name);
}
