package infrastructure.security;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import bll.UserService;

public class MarketsSimServerAuthProvider implements AuthenticationProvider {

	// TODO: add
	private UserService _userService;
	private Md5PasswordEncoder _passwordEncoder;
	
	public MarketsSimServerAuthProvider(UserService userService) {
		// TODO: implement dependency injection
		_userService = userService;
		_passwordEncoder = new Md5PasswordEncoder();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        User user;
        
        try {
        	user = _userService.loadUserByUsername(username);
        } catch(UsernameNotFoundException notFoundEx) {
        	throw new BadCredentialsException("Username not found.");
        }

        //String encodedPassowrd = _passwordEncoder.encodePassword(user.getPassword(), getUserSaltForEncode(user));
        if (!_passwordEncoder.isPasswordValid(user.getPassword(), password, getUserSaltForEncode(user))) {
        	throw new BadCredentialsException("Wrong password.");
        }
        
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	// Private methods
	private Object getUserSaltForEncode(User user) {
		ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("username");
        Object salt = saltSource.getSalt(user);     
		return salt;
	}
}
