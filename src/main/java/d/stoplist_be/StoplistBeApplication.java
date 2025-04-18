package d.stoplist_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StoplistBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoplistBeApplication.class, args);
    }

}
