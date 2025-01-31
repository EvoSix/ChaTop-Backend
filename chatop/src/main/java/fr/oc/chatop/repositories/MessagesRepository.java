package fr.oc.chatop.repositories;

import fr.oc.chatop.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

}
