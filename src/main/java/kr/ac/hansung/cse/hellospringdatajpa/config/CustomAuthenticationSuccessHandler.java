package kr.ac.hansung.cse.hellospringdatajpa.config; // 또는 kr.ac.hansung.cse.hellospringdatajpa.handler

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import java.io.IOException;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    private final FlashMapManager flashMapManager = new SessionFlashMapManager();

    public CustomAuthenticationSuccessHandler() {

        setDefaultTargetUrl("/products");
        setAlwaysUseDefaultTargetUrl(false);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        String username = "";
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else {
            username = authentication.getName();
        }


        FlashMap flashMap = new FlashMap();
        flashMap.put("loginSuccessMessage", "로그인에 성공하였습니다." + username + "님, 환영합니다!");


        flashMapManager.saveOutputFlashMap(flashMap, request, response);


        super.onAuthenticationSuccess(request, response, authentication);
    }
}