package com.lxp.auth.presentation.rest.common;

import com.lxp.auth.domain.common.exception.AuthErrorCode;
import com.lxp.auth.domain.common.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-v1/error")
public class AuthenticationErrorController {

    @GetMapping("/access-denied")
    public void accessDenied(HttpServletRequest request) {
        AccessDeniedException originalException =
            (AccessDeniedException) request.getAttribute(WebAttributes.ACCESS_DENIED_403);

        String message = (originalException != null)
            ? originalException.getMessage() : "권한이 없는 접근입니다.";

        throw new AuthException(AuthErrorCode.ACCESS_DENIED, message);
    }

    @GetMapping("/authentication-failed")
    public void authenticationFailed(HttpServletRequest request) {
        AuthenticationException originalException =
            (AuthenticationException) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        String message = (originalException != null)
            ? originalException.getMessage() : "인증에 실패했습니다. 토큰을 확인해주세요.";

        throw new AuthException(AuthErrorCode.UNAUTHORIZED_ACCESS, message);
    }

}
