package br.com.fatec.ellentex.livrariales.controle.web;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

public class SessionListener implements HttpSessionListener {
    private int sessionCount = 0;
    public static List<HttpSession> listaSessao = new ArrayList<>();


    public void sessionCreated(HttpSessionEvent event) {
        synchronized (this) {
            sessionCount++;
            listaSessao.add(event.getSession());
        }
        System.out.println("Session Created: " + event.getSession().getId());
        System.out.println("Total Sessions: " + sessionCount);
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        synchronized (this) {
            sessionCount--;
            listaSessao.remove(event.getSession());
        }
        System.out.println("Session Destroyed: " + event.getSession().getId());
        System.out.println("Total Sessions: " + sessionCount);
    }
}
