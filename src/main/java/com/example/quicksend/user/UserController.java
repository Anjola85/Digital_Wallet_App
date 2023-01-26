package com.example.quicksend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.quicksend.util.helper.handleControllerResult;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final handleControllerResult result;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.result = new handleControllerResult();
    }

    /**
     *
     * @return all registered users in the database
     */
    @GetMapping("/all-users")
    public ResponseEntity<?>  getUsers() {
        return result.handleResult(userService.getAllUsers());
    }

    /**
     * Gets one user by Id
     * @param userId - provided userId
     * @return user data
     */
    @GetMapping("{userId}")
    public ResponseEntity<?>  getUser(@PathVariable("userId") Long userId) {
        return result.handleResult(userService.getUser(userId));
    }

    /**
     * TODO: remember to add JWT
     * @return message - success or failed
     */
    @PostMapping("/register-user")
    public  ResponseEntity<?> registerUser(@RequestBody UserDTO newUser) {
        // TODO: JWT implementation goes here, add jwtToken and HrrpHeaders.Auth to header
        return result.handleUserRegistration(userService.registerUser(newUser), HttpHeaders.AUTHORIZATION, "");
    }

    /**
     *
     * @param userId
     * @param updateRequest
     * @return
     */
    @PutMapping("update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO updateRequest) {
        return result.handleResult(userService.updateUser(userId, updateRequest));
    }

}
