package com.tms.service.impl;

import com.tms.service.CookieService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieServiceImpl implements CookieService {

    public String getLastDateTimeAccess(HttpServletRequest req) {
        String cookieName = "lastAccessTime";
        return  Arrays.stream(this.getCookies(req).orElse(new Cookie[]{}))
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue).findFirst().orElse(null);
    }
    private Optional<Cookie[]> getCookies(HttpServletRequest req) {
        return Optional.ofNullable(req.getCookies());
    }
}
