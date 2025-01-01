package fr.oc.chatop.repos;


import fr.oc.chatop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
