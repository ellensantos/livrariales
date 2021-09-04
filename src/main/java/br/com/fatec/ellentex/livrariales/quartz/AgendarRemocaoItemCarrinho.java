package br.com.fatec.ellentex.livrariales.quartz;

import org.quartz.*;

public class AgendarRemocaoItemCarrinho extends AgendadorQuartz {
    public static void agendar() {
        try {
            JobDetail job = JobBuilder.newJob(RemoverItemCarrinhoJOB.class)
                    .withIdentity("RemoverItemCarrinhoJOB", "grupoCarrinho")
                    .requestRecovery(true)
                    .build();

            SimpleScheduleBuilder simpleScheduler = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(1)
                    .repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("RemoverItemCarrinhoTRIGGER","grupoCarrinho")
                    .withSchedule(simpleScheduler)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
