package com.rungroup.web.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getSessionUser() {
        /* Wyciąga z SecurityContext, Authentication zawierający informacje o aktualnie zalogowanym uzytkowniku */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /* Sprawdza czy pobrane dane, są faktycznie zalogwoanego użytkownika, a nie "Anonymous" czyli anonimowy user */
        if(!(authentication instanceof AnonymousAuthenticationToken)) {

            /* Jeśli użytkownik jest zalogowany to pobieramy jego username i zwracamy */
            String currentUsername = authentication.getName();
            return currentUsername;
        }
        return null;
    }
}
