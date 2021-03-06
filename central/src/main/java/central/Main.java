package central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.nio.file.Path;
import java.nio.file.Paths;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"central.controllers"})
public class Main {

	public static void main(String[] args) {

		SpringApplication.run(Main.class, args);

	}

}

