package com.lxp.auth.application.local.port.required;

import com.lxp.auth.application.local.port.required.dto.AuthDomainInfo;

public interface UserAuthSearchPort {

    AuthDomainInfo retrieveAuthorityByUserId(String userId);

    AuthDomainInfo retrieveAuthorityByEmail(String email);

}
