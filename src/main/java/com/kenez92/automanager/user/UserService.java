package com.kenez92.automanager.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUserName(userName);
        if (user.isPresent()) {
            return userMapper.mapToUserDto(user.get());
        }
        throw new UsernameNotFoundException("User not found");
    }

    UserDto getUserByUserName(String userName) {
        Optional<User> user = userRepository.findUserByUserName(userName);
        if (user.isPresent()) {
            return userMapper.mapToInnerUserDto(user.get());
        }
        throw new UsernameNotFoundException("User not found");
    }


    public UserDto createUser(UserDto userDto) {
        if (userDto != null && userDto.getUserName() != null && userDto.getPassword() != null) {
            String password = bCryptPasswordEncoder.encode(userDto.getPassword());
            User user = userMapper.mapToUser(userDto);
            user.setPassword(password);
            user.setRole(UserRole.ROLE_USER);
            User savedUser = userRepository.save(user);
            return userMapper.mapToInnerUserDto(savedUser);
        }
        throw new RuntimeException("Username and password cannot be empty");
    }
}
