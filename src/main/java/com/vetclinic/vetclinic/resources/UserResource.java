package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.UserDTO;
import com.vetclinic.vetclinic.dtos.LoginDTO;
import com.vetclinic.vetclinic.models.User;
import com.vetclinic.vetclinic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vetclinic/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(userService.convertUserToUserDTO(user));
    }

    @Transactional()
    @GetMapping("/name")
    public ResponseEntity<List<UserDTO>> findUserByName(@RequestParam String name) {
        UserDTO userDTO = userService.findUserByName(name);
        return ResponseEntity.ok(List.of(userDTO));
    }

    // Register (create)
    @Transactional
    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    // Login
    @Transactional(readOnly = true)
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.authenticateByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok(userService.convertUserToUserDTO(user));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@RequestBody UserDTO userDTO)  {
        userService.deleteUser(userDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
