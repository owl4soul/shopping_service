package com.github.owl4soul.shopping_service;


import com.github.owl4soul.shopping_service.domain.Role;
import com.github.owl4soul.shopping_service.domain.User;
import com.github.owl4soul.shopping_service.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Загрузчик дефолтных значений в базу данных.
 */
@Component
public class DataLoader implements InitializingBean {

    @Resource
    private DataLoader dataLoader;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        dataLoader.createDefaultUsers();
    }

    /**
     * Создание дефолтных пользователей: admin и user, если их нет в базе данных.
     */
    @Transactional
    public void createDefaultUsers() {
        User user;

        if (!userRepository.existsByUsername("admin")) {
            user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("password"));
            user.setEnabled(true);
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }

        if (!userRepository.existsByUsername("user")) {
            user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("123"));
            user.setEnabled(true);
            user.setRole(Role.USER);
            userRepository.save(user);
        }
    }
}
