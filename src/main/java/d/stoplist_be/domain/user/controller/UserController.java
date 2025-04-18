package d.stoplist_be.domain.user.controller;

import d.stoplist_be.domain.user.dto.UserLoginRequest;
import d.stoplist_be.domain.user.dto.UserResponse;
import d.stoplist_be.domain.user.dto.UserSignUpRequest;
import d.stoplist_be.domain.user.service.UserService;
import d.stoplist_be.global.dto.ApiResponse;
import d.stoplist_be.global.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static d.stoplist_be.global.enums.SuccessCode.USER_LOGIN_SUCCESS;
import static d.stoplist_be.global.enums.SuccessCode.USER_SIGNUP_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<?> signUp(@RequestBody UserSignUpRequest request) {
        return ResponseUtils.success(USER_SIGNUP_SUCCESS, userService.signUp(request));
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody UserLoginRequest request) {
        return ResponseUtils.success(USER_LOGIN_SUCCESS, userService.login(request));
    }
}
