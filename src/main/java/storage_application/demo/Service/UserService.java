package storage_application.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import storage_application.demo.Mapper.UserMapper;
import storage_application.demo.Model.User;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        String encodedPwd = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encodedPwd);
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        return userMapper.insert(newUser);
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}