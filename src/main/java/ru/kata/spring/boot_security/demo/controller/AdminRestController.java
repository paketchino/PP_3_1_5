package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDTO;
import ru.kata.spring.boot_security.demo.mapper.Mapper;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final Mapper mapper;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(Mapper mapper, UserService userService, RoleService roleService) {
        this.mapper = mapper;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> showAllUser() {
        return new ResponseEntity<>(
                userService.findAll()
                .stream().map(mapper::convertTo)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return new ResponseEntity<>(
                roleService.getAllRoles()
                        .stream()
                        .map(mapper::convertTo)
                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Collection<Role>> getMyRoles(@PathVariable("id") Long id) {
        Collection<Role> roleCollections = new HashSet<>();
        roleCollections.add((Role) userService.findById(id).getRoles());
        return new ResponseEntity<>(roleCollections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserUpdateDTO> getUserId(@PathVariable("id") Long id) {
        User byId = userService.findById(id);
        return new ResponseEntity<>(mapper.convertToUpdate(byId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserUpdateDTO> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.ok(mapper.convertToUpdate(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
