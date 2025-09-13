package com.vetclinic.vetclinic.services;

import com.vetclinic.vetclinic.dtos.UserDTO;
import com.vetclinic.vetclinic.models.User;
import com.vetclinic.vetclinic.Enum.Role;
import com.vetclinic.vetclinic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static java.util.Objects.isNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertUserDTOtoUser(userDTO);

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        user = userRepository.save(user);
        return convertUserToUserDTO(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public UserDTO findUserByName(String name) {
        return convertUserToUserDTO(userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        if (isNull(userDTO.getId())) {
            throw new IllegalArgumentException("User not found");
        }
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setCpf(userDTO.getCpf());
        user.setName(userDTO.getName());
        user.setOfficeHours(userDTO.getOfficeHours());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getRole() != null) {
            user.setRole(Role.valueOf(userDTO.getRole()));
        }

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        user = userRepository.save(user);
        return convertUserToUserDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO convertUserToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setCpf(user.getCpf());
        dto.setName(user.getName());
        dto.setOfficeHours(user.getOfficeHours());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);
        return dto;
    }

    public User convertUserDTOtoUser(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setCpf(dto.getCpf());
        user.setName(dto.getName());
        user.setOfficeHours(dto.getOfficeHours());
        user.setEmail(dto.getEmail());
        if (dto.getRole() != null) {
            user.setRole(Role.valueOf(dto.getRole()));
        } else {
            user.setRole(Role.USER);
        }
        return user;
    }

    public User authenticateByEmailAndPassword(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Credentials not set for this user");
        }
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return user;
    }
}
