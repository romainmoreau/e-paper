package fr.romainmoreau.epaper.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.jssc.JsscEPaperClient;

@SpringBootApplication
public class EPaperApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(EPaperApplication.class);

	@Value("${e-paper-client.port-name}")
	private String portName;

	@Bean
	public EPaperClient ePaperClient() throws IOException {
		LOGGER.info("Creating e-paper client using port name {}", portName);
		return new JsscEPaperClient(portName);
	}

	public static final void main(String[] args) throws Exception {
		SpringApplication.run(EPaperApplication.class, args);
	}
}
