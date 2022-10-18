package otus.msa.crud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import otus.msa.crud.dto.ErrorDto;
import otus.msa.crud.dto.UserDto;
import otus.msa.crud.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @PostMapping
    public ErrorDto createUser(@RequestBody UserDto userDto) {
        log.info("Create user " + userDto);
        return userService.saveUser(userDto);
    }

    @GetMapping
    @ResponseBody
    public UserDto getUser(@RequestParam Long userId) {
        log.info("Get user {}", userId);
        return userService.getUser(userId);
    }

    @DeleteMapping
    public ErrorDto deleteUser(@RequestParam Long userId) {
        log.info("Delete user {}", userId);
        return userService.deleteUser(userId);
    }

    @PutMapping
    @ResponseBody
    public UserDto putUser(@RequestParam Long userId, @RequestBody UserDto userDto) {
        log.info("Update user");
        return new UserDto();
    }
}
