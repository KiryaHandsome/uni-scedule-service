package by.bsuir.schedule.service;

import by.bsuir.schedule.dto.LoginRequest;
import by.bsuir.schedule.dto.TokenResponse;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService tokenService;

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {
        User user = userDao.findByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RestException("Provided password is not valid.", HttpStatus.UNAUTHORIZED);
        }

        String token = tokenService.generateToken(user.getEmail());
        return new TokenResponse(token);
    }
}
