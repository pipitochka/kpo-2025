package hse;

import hse.domains.facade.HseFacade;
import hse.domains.object.HseCommandContext;
import hse.emums.CommandType;
import hse.emums.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

import java.util.Scanner;
import java.util.stream.IntStream;

@SpringBootApplication
public class KpoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(KpoApplication.class, args);
        ConsoleApplicartion consoleApplicartion = context.getBean(ConsoleApplicartion.class);
        consoleApplicartion.run();
    }


}