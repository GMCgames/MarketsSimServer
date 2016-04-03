package infrastructure.security.helpers;

import org.springframework.security.core.userdetails.User;

import bll.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenHelper {
	private final String SECRET;
    private final UserService _userService;

    public TokenHelper(String secret, UserService userService) {
        this.SECRET = secret;
        this._userService = userService;
    }

    /**
     * Returns the User identified by the provided authentication token
     */
    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return _userService.loadUserByUsername(username);
    }

    /**
     * Generates an authentication token for the provided User
     */
    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
