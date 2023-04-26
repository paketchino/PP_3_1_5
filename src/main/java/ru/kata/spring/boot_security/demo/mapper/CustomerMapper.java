package ru.kata.spring.boot_security.demo.mapper;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.dto.UserUpdateDTO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Set;

@Component
public class CustomerMapper implements Mapper {

    @Override
    public UserDTO convertTo(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles((Set<Role>) user.getRoles());
        return userDTO;
    }

    @Override
    public RoleDTO convertTo(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    @Override
    public UserUpdateDTO convertToUpdate(User user) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setId(user.getId());
        userUpdateDTO.setAge(user.getAge());
        userUpdateDTO.setLastName(user.getLastName());
        userUpdateDTO.setUsername(user.getUsername());
        userUpdateDTO.setEmail(user.getEmail());
        userUpdateDTO.setPassword(user.getPassword());
        userUpdateDTO.setRoles((Set<Role>) user.getRoles());
        return userUpdateDTO;
    }
}
