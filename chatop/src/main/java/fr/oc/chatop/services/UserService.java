package fr.oc.chatop.services;
import fr.oc.chatop.dto.RentalResponseDTO;
import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.entity.Rental;
import fr.oc.chatop.entity.User;
import fr.oc.chatop.mapper.UserMapper;
import fr.oc.chatop.repos.UserRepos;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService
{
    private final UserRepos userRepos;
    private final UserMapper userMapper;

    public UserService(UserRepos userRepos, UserMapper userMapper) {
        this.userRepos = userRepos;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepos.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepos.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public UserResponseDTO createUser(UserResponseDTO userResponseDTO) {
        User user = userMapper.toEntity(userResponseDTO);
        User savedUser = userRepos.save(user);
        return userMapper.toDto(savedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepos.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepos.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new fr.oc.chatop.entity.UserDetails(user);
    }
}
