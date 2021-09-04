package br.com.fatec.ellentex.livrariales.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;

/**
 * @author EllenTex
 */
public class AgendadorQuartz {

    protected static SchedulerFactory shedFact = new StdSchedulerFactory();
    protected static Scheduler scheduler;
    private static Map<Integer, String> mapJobs;


    public static void agendar(){
        try {
            scheduler = shedFact.getScheduler();
            AgendarRemocaoItemCarrinho.agendar();
            AgendarVerificacaoAprovacaoPedido.agendar();
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static Scheduler getScheduler() {
        return scheduler;
    }

    public static Map<Integer, String> getMapJobs() {
        return mapJobs;
    }
}
