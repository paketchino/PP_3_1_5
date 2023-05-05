package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.mapper.Mapper;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class RoleRestController {

    private final RoleService roleService;
    private final UserService userService;

    private final Mapper mapper;

    public RoleRestController(RoleService roleService, UserService userService, Mapper mapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return new ResponseEntity<>(
                roleService.getAllRoles()
                        .stream()
                        .map(mapper::convertTo)
                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<Collection<RoleDTO>> getMyRoles(@PathVariable("id") Long id) {
        Collection<RoleDTO> roleCollections = new HashSet<>();
        Role roles = (Role) userService.findById(id).getRoles();
        RoleDTO roleDTO = mapper.convertTo(roles);
        roleCollections.add(roleDTO);
        return new ResponseEntity<>(roleCollections, HttpStatus.OK);
    }


}
