package fr.oc.chatop.repos;
import fr.oc.chatop.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

}
