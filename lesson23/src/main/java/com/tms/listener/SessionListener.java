package com.tms.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Создать листенеры для логирования при создания сессии.
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("Session with id:" + session.getId() + "was created!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
