package br.com.fatec.ellentex.livrariales.quartz;

import org.quartz.*;

public class AgendarVerificacaoAprovacaoPedido extends AgendadorQuartz {
    public static void agendar() {
        try {
            JobDetail job = JobBuilder.newJob(VerificarAprovacaoPedidoJOB.class)
                    .withIdentity("VerificarAprovacaoPedidoJOB", "grupoPedido")
                    .build();

            SimpleScheduleBuilder simpleScheduler = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInMinutes(5)
                    .repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("VerificarAprovacaoPedidoTRIGGER","grupoPedido")
                    .withSchedule(simpleScheduler)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
