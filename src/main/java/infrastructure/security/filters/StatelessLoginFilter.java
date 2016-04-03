package infrastructure.security.filters;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import bll.AuthenticationService;
import bll.UserService;
import bll.model.MarketsSimUser;
import bll.model.UserAuthentication;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter  {

	private final UserService _userService;
	private final AuthenticationService _authenticationService;
	
	public StatelessLoginFilter(String urlMapping,
			UserService userService, 
			AuthenticationService authenticationService,
			AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
        _userService = userService;
        _authenticationService = authenticationService;
        
        setAuthenticationManager(authManager);
    }
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authentication) throws IOException, ServletException {
	 
		// Lookup the complete User object from the database and create an Authentication for it
		final MarketsSimUser authenticatedUser = _userService.loadUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);
	 
		// Add the custom token as HTTP header to the response
		_authenticationService.addAuthentication(response, userAuthentication);
	 
		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		final UsernamePasswordAuthenticationToken loginToken = 
				new UsernamePasswordAuthenticationToken(username, password);
		
		return getAuthenticationManager().authenticate(loginToken);
	}
}
