package hse.kpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main class.
 */
@SpringBootApplication
public class KpoApplication {
    /**
     * point to entry in application.
     *
     * @param args special args.
     */
    public static void main(String[] args) {
        SpringApplication.run(KpoApplication.class, args);
    }
}