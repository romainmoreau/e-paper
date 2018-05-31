package fr.romainmoreau.epaper.web.jssc;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.jssc.JsscEPaperClient;

@SpringBootApplication(scanBasePackages = "fr.romainmoreau.epaper.web")
public class JsscEPaperApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsscEPaperApplication.class);

	@Autowired
	private JsscEPaperProperties ePaperProperties;

	@Bean
	public EPaperClient ePaperClient() throws IOException {
		LOGGER.info("Creating JSSC e-paper client using port name {}, timeout {} and receive timeout {}",
				ePaperProperties.getPortName(), ePaperProperties.getTimeout(), ePaperProperties.getReceiveTimeout());
		return new JsscEPaperClient(ePaperProperties.getPortName(), ePaperProperties.getTimeout(),
				ePaperProperties.getReceiveTimeout());
	}

	public static final void main(String[] args) throws Exception {
		SpringApplication.run(JsscEPaperApplication.class, args);
	}
}
