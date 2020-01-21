package com.challenge.tipocambio.security.token;

import io.reactivex.Single;

public interface SessionTokenService {
    Single<SessionTokenDto> createSessionToken(String userName, String userKey);
    Boolean validateSessionToken(String sessionToken);
}
