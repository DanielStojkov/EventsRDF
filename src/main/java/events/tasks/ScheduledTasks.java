package events.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Daniel on 8/4/2016.
 */
@Component
public class ScheduledTasks {

    @Scheduled(cron = "0 0 8 * * *")
    public void example() {

    }
}
