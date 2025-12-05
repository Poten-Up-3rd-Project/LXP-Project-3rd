package com.lxp.auth.presentation.rest.local;

import com.lxp.auth.application.local.port.required.usecase.AuthenticateUserUseCase;
import com.lxp.auth.application.local.port.required.usecase.RegisterUserUseCase;
import com.lxp.auth.presentation.rest.local.dto.reqeust.LoginRequest;
import com.lxp.auth.presentation.rest.local.dto.reqeust.RegisterRequest;
import com.lxp.auth.presentation.rest.local.dto.response.LoginResponse;
import com.lxp.common.infrastructure.exception.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LocalAuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    public LocalAuthController(AuthenticateUserUseCase authenticateUserUseCase, RegisterUserUseCase registerUserUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        String accessToken = authenticateUserUseCase.authenticate(request.toCommand());
        return ApiResponse.success(new LoginResponse(accessToken));
    }

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegisterRequest request) {
        registerUserUseCase.register(request.toCommand());
        return ApiResponse.success();
    }
}
