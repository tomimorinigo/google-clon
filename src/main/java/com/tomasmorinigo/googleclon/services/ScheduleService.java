package com.tomasmorinigo.googleclon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleService {
    
    @Autowired
    private SpiderService spiderService;

    @Scheduled(cron = "0 0 0 * * ?") // every day at 00:00:00
    public void scheduleIndexWebPages(){
        spiderService.indexWebPages();
    }

}
