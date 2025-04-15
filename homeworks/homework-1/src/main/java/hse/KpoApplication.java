package hse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * entry class of application.
 */
@SpringBootApplication
public class KpoApplication {

    /**
     * entry point.
     *
     * @param args of command line.
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(KpoApplication.class, args);
        ConsoleApplicartion consoleApplicartion = context.getBean(ConsoleApplicartion.class);
        consoleApplicartion.run();
    }


}