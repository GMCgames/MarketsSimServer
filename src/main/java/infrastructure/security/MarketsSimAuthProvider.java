package infrastructure.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import bll.UserService;
import bll.model.MarketsSimUser;

public class MarketsSimAuthProvider implements AuthenticationProvider {

	// TODO: add
	private UserService _userService;
	private Md5PasswordEncoder _passwordEncoder;
	
	public MarketsSimAuthProvider(UserService userService) {
		// TODO: implement dependency injection
		_userService = userService;
		_passwordEncoder = new Md5PasswordEncoder();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        MarketsSimUser user;
        
        try {
        	user = _userService.loadUserByUsername(username);
        } catch(UsernameNotFoundException notFoundEx) {
        	throw new BadCredentialsException("Username not found.");
        }

        //String encodedPassowrd = _passwordEncoder.encodePassword(user.getPassword(), getUserSaltForEncode(user));
        if (!_passwordEncoder.isPasswordValid(user.getPassword(), password, getUserSaltForEncode(user))) {
        	throw new BadCredentialsException("Wrong password.");
        }
        
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	// Private methods
	private Object getUserSaltForEncode(MarketsSimUser user) {
		ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("username");
        Object salt = saltSource.getSalt(user);     
		return salt;
	}
}
