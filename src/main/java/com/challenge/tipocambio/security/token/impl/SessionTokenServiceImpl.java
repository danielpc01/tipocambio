package com.challenge.tipocambio.security.token.impl;

import com.challenge.tipocambio.commons.utils.NumberUtils;
import com.challenge.tipocambio.persistence.model.User;
import com.challenge.tipocambio.persistence.repository.UserJpaRepository;
import com.challenge.tipocambio.security.token.SessionTokenDto;
import com.challenge.tipocambio.security.token.SessionTokenService;
import com.challenge.tipocambio.security.token.impl.enums.SessionTokenErrorCodes;
import com.challenge.tipocambio.security.token.impl.enums.UserErrorCodes;
import com.challenge.tipocambio.security.token.impl.exception.SessionNotFoundException;
import com.challenge.tipocambio.security.token.impl.jwt.JWTComponent;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionTokenServiceImpl implements SessionTokenService {

    private static final int JWT_TIME_TO_LIVE = 200000;
    private static final String TOKEN_SESSION = "TOKEN_SESSION";

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public SessionTokenServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;

    }

    @Override
    public Single<SessionTokenDto> createSessionToken(String userName, String userKey) {
        return Single.create(singleSubscriber -> {
            Optional<User> userOptional = userJpaRepository.findByUserNameAndKeyAndEnabled(userName,userKey,Boolean.TRUE);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                singleSubscriber.onSuccess(
                        SessionTokenDto
                                .builder()
                                .key(JWTComponent.createJWT(String.valueOf(user.getId()),user.getName(), TOKEN_SESSION, JWT_TIME_TO_LIVE))
                                .build());
            } else {
                singleSubscriber.onError(new SessionNotFoundException(UserErrorCodes.USER_OR_KEY_INVALID));
            }
        });
    }

    @Override
    public Boolean validateSessionToken(String sessionToken) {
        if (!Optional.ofNullable(JWTComponent.decodeJWT(sessionToken)).isPresent()) {
            throw new SessionNotFoundException(SessionTokenErrorCodes.SESSION_TOKEN_INVALID);
        }
        return Boolean.TRUE;
    }

}
