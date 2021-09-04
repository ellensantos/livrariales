package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.quartz.AgendadorQuartz;
import org.quartz.SchedulerException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author EllenTex
 */
public class ListenerServlet implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        //AgendarRemocaoItemCarrinho.agendar();
        AgendadorQuartz.agendar();
    }

    public void contextDestroyed(ServletContextEvent sce) {

        try {
            if(AgendadorQuartz.getScheduler() != null)
            AgendadorQuartz.getScheduler().shutdown(false);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}