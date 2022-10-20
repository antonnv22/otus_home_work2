package otus.msa.crud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import otus.msa.crud.ResourceNotFoundException;
import otus.msa.crud.dto.ErrorDto;
import otus.msa.crud.dto.UserDto;
import otus.msa.crud.mapper.UserMapper;
import otus.msa.crud.model.User;
import otus.msa.crud.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public ErrorDto saveUser(UserDto userDto) {
        User user = userMapper.toModel(userDto);
        log.info("Save user " + user);
        User newUser = userRepository.save(user);
        log.info("Saved user " + newUser);
        return new ErrorDto();
    }
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User not found " + userId));;
        log.info("Get user " + user);
        return userMapper.toDto(user);
    }

    public ErrorDto deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return new ErrorDto();
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userMapper.toModel(userDto);
        user.setId(userId);
        User newUser = userRepository.save(user);
        log.info("update user " + newUser);
        return userMapper.toDto(newUser);
    }
}
