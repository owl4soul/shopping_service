package com.github.owl4soul.shopping_service.security;

import com.github.owl4soul.shopping_service.domain.Role;
import com.github.owl4soul.shopping_service.domain.User;
import com.github.owl4soul.shopping_service.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Кастомная имплементация UserDetailsService.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;


    /**
     * Загрузка пользователя по юзернейму (логину).
     * @param username логин
     * @return спринговый User с данными пользвоателя из бд.
     * @throws UsernameNotFoundException возникает, если пользователь с таким username не был найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getGrantedAutorities(user));
        } else {
            throw new UsernameNotFoundException(username + " not found!");
        }
    }

    /**
     * Получение прав доступа для пользователей из бд на основе их ролей
     * @param user пользователь из бд
     * @return список прав доступа
     */
    private List<GrantedAuthority> getGrantedAutorities(User user) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user.getRole() == null) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_NOT_ACTIVATE"));
        } else if (user.getRole() == Role.USER) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (user.getRole() == Role.ADMIN) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE ADMIN"));
        }

        return grantedAuthorities;
    }
}
