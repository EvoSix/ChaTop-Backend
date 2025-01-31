package fr.oc.chatop.services;
import fr.oc.chatop.dto.UserRequestDTO;
import fr.oc.chatop.dto.UserResponseDTO;
import fr.oc.chatop.entities.User;
import fr.oc.chatop.mapper.UserMapper;
import fr.oc.chatop.repositories.UserRepository;
import fr.oc.chatop.services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.Optional;


@Service
public class UserService implements IUserService
{
    private final UserRepository userRepos;
    private final UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepos, UserMapper userMapper) {
        this.userRepos = userRepos;
        this.userMapper = userMapper;
    }





    public UserResponseDTO getUserById(Long id) {
        User user = userRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public void createUser(UserRequestDTO userRequestDTO) {
        Optional<User> foundUser= userRepos.findByEmail(userRequestDTO.getEmail());
        if(foundUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        if(userRequestDTO.getName()==null && userRequestDTO.getPassword()==null) {
            throw new RuntimeException( "required fields are mandatory: Name and Password");
        }


        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(this.passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setName(userRequestDTO.getName());
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());

        //Save en BDD
        userRepos.save(user);
    }




}
