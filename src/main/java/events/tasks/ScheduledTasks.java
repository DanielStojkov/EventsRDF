package events.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * A component that runs on a schedule.
 */
@Component
public class ScheduledTasks {

    @Scheduled(cron = "0 0 8 * * *")
    public void example() {

    }
}
