package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.AuthorizedUser;
import com.ruslanshakirov.crm.dto.UserRequest;
import com.ruslanshakirov.crm.dto.UserResponse;
import com.ruslanshakirov.crm.entity.Role;
import com.ruslanshakirov.crm.entity.User;
import com.ruslanshakirov.crm.exception.MyNotFoundException;
import com.ruslanshakirov.crm.mappers.UserMapper;
import com.ruslanshakirov.crm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ruslanshakirov.crm.exception.ExceptionMessages.NOT_FOUND_EMAIL;
import static com.ruslanshakirov.crm.util.AuthUtil.getCurrentUserEmail;
import static com.ruslanshakirov.crm.util.ValidationUtil.checkNotFound;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findById(Long id) {
        return checkNotFound(userRepository.findById(id), "Пользователь", id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new MyNotFoundException(String.format(NOT_FOUND_EMAIL, email)));
    }

    public User getCurrentUser() {
        return findByEmail(getCurrentUserEmail());
    }

    @Transactional
    public void remove(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Transactional
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.addRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return new AuthorizedUser(user);
    }
}
