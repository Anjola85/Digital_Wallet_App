package com.example.quicksend.user;

import com.example.quicksend.config.BaseController;
import com.example.quicksend.util.dto.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.quicksend.util.helper.handleControllerResult;

@RestController
@RequestMapping("/api/user")
public class UserController implements BaseController<UserDTO>{
    private final UserService userService;
    private final handleControllerResult<UserDTO> result;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.result = new handleControllerResult<>();
    }

    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok().body("Welcome to the user api home!");
    }

    /**
     * TODO: remember to add JWT
     * @return message - success or failed
     */
    @PostMapping("/register-user")
    public ResponseEntity<?> create(@RequestBody UserDTO newUser) {
        // TODO: JWT implementation goes here, add jwtToken and HttpHeaders.Auth to header
        return result.handleUserRegistration(userService.create(newUser), HttpHeaders.AUTHORIZATION, "");
    }

    /**
     *
     * @param userId
     * @param updateRequest
     * @return
     */
    @PutMapping("update/{userId}")
    public ResponseEntity<?> update(@PathVariable("userId") Long userId, @RequestBody UserDTO updateRequest) {
        return result.handleResult(userService.update(userId, updateRequest));
    }

    /**
     *
     * @return all registered users in the database
     */
    @GetMapping("/all-users")
    public ResponseEntity<?>  findAll() {
        return result.handleResult(userService.findAll());
    }

    /**
     * Gets one user by Id
     * @param userId - provided userId
     * @return user data
     */
    @GetMapping("{userId}")
    public ResponseEntity<?>  findById(@PathVariable("userId") Long userId) {
        return result.handleResult(userService.findById(userId));
    }
}
