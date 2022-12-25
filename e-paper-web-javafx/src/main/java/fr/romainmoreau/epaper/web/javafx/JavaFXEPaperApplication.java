package fr.romainmoreau.epaper.web.javafx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.javafx.JavaFXEPaperClient;
import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication(scanBasePackages = "fr.romainmoreau.epaper.web")
@ConfigurationPropertiesScan
public class JavaFXEPaperApplication extends Application {
	private static String[] args;

	private static Stage primaryStage;

	private static ConfigurableApplicationContext configurableApplicationContext;

	@Bean
	EPaperClient ePaperClient() {
		return new JavaFXEPaperClient(primaryStage);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		JavaFXEPaperApplication.primaryStage = primaryStage;
		configurableApplicationContext = SpringApplication.run(JavaFXEPaperApplication.class, args);
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		configurableApplicationContext.close();
	}

	public static final void main(String[] args) throws Exception {
		JavaFXEPaperApplication.args = args;
		launch(args);
	}
}
