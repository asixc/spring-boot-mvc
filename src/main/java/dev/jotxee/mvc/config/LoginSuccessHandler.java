package dev.jotxee.mvc.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
										final HttpServletResponse response,
										final Authentication authentication) throws IOException, ServletException {

		final SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
		final FlashMap flashMap = new FlashMap();
		flashMap.put("success", "Hola " +authentication.getName()+ ", haz iniciado sesión con éxito!");
		flashMapManager.saveOutputFlashMap(flashMap, request, response);

		if(authentication != null) {
			logger.info("El usuario '"+authentication.getName()+"' ha iniciado sesión con éxito");
		}

		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			response.sendRedirect("/manager");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
