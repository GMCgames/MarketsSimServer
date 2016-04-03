package bll;
import dal.dao.UserDAO;
import dal.repo.UserRepository;
import infrastructure.security.helpers.TokenHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import bll.model.UserAuthentication;

public class AuthenticationService {

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private final TokenHelper _tokenHelper;
	
	public AuthenticationService(String secret, UserService userService) {
		// TODO add dependency injection
		_tokenHelper = new TokenHelper(secret, userService);
	}
	
	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, _tokenHelper.createTokenForUser(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = _tokenHelper.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}


