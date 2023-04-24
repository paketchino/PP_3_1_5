package ru.kata.spring.boot_security.demo.service.inter;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserService {

    User saveUser(User user);
    void deleteById(Long id);
    List<User> findAll();
    User findById(Long id);
    void update(Long id, User updatedUser);
}
