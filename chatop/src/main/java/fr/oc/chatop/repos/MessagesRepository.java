package fr.oc.chatop.repos;

import fr.oc.chatop.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    List<Messages> findByRentalId(Long rentalId);
    List<Messages> findByUserId(Long userId);
}
