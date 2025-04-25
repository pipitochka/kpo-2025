package zoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * main application.
 */
@SpringBootApplication
public class KpoApplication {
    /**
     * entry point of program.
     *
     * @param args of console application.
     */
    public static void main(String[] args) {
        SpringApplication.run(KpoApplication.class, args);
    }
}
