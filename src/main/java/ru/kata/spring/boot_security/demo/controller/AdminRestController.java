package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDTO;
import ru.kata.spring.boot_security.demo.mapper.Mapper;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final Mapper mapper;
    private final UserService userService;

    public AdminRestController(Mapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> showAllUser() {
        return new ResponseEntity<>(
                userService.findAll()
                .stream().map(mapper::convertTo)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<UserUpdateDTO> getUserId(@PathVariable("id") Long id) {
        User byId = userService.findById(id);
        return new ResponseEntity<>(mapper.convertToUpdate(byId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/user")
    public ResponseEntity<UserUpdateDTO> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.ok(mapper.convertToUpdate(user));
    }

    @DeleteMapping("/{id}/user")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
