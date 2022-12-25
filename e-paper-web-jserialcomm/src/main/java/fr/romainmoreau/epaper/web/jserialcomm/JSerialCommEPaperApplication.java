package fr.romainmoreau.epaper.web.jserialcomm;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.client.jserialcomm.JSerialCommEPaperClient;

@SpringBootApplication(scanBasePackages = "fr.romainmoreau.epaper.web")
@ConfigurationPropertiesScan
public class JSerialCommEPaperApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(JSerialCommEPaperApplication.class);

	@Autowired
	private JSerialCommEPaperProperties ePaperProperties;

	@Bean
	EPaperClient ePaperClient() throws IOException {
		LOGGER.info("Creating jSerialComm e-paper client using port name {}, timeout {} and receive timeout {}",
				ePaperProperties.getPortName(), ePaperProperties.getTimeout(), ePaperProperties.getReceiveTimeout());
		return new JSerialCommEPaperClient(ePaperProperties.getPortName(), ePaperProperties.getTimeout(),
				ePaperProperties.getReceiveTimeout());
	}

	public static final void main(String[] args) throws Exception {
		SpringApplication.run(JSerialCommEPaperApplication.class, args);
	}
}
