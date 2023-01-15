package com.tms.service;

import javax.servlet.http.HttpServletRequest;

public interface CookieService {
     String getLastDateTimeAccess(HttpServletRequest req);
}
