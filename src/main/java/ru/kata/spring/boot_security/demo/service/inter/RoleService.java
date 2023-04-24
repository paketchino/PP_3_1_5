package ru.kata.spring.boot_security.demo.service.inter;

import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {

    @Query("from Role ")
    List<Role> getAllRoles();
}
