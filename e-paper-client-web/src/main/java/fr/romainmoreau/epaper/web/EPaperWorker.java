package fr.romainmoreau.epaper.web;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.romainmoreau.epaper.client.api.EPaperClient;
import fr.romainmoreau.epaper.jaxb.api.Command;
import fr.romainmoreau.epaper.jaxb.api.Commands;

@Service
public class EPaperWorker {
	private static final Logger LOGGER = LoggerFactory.getLogger(EPaperWorker.class);

	@Autowired
	private EPaperClient ePaperClient;

	private final Thread thread;

	private final BlockingQueue<Commands> commandsQueue;

	private volatile boolean stop;

	public EPaperWorker() {
		thread = new Thread(this::run, "e-paper");
		commandsQueue = new LinkedBlockingQueue<>();
	}

	@PostConstruct
	private void postConstruct() {
		thread.start();
	}

	@PreDestroy
	private void preDestroy() {
		commandsQueue.clear();
		stop = true;
		thread.interrupt();
	}

	public boolean offer(final Commands commands) {
		return commandsQueue.offer(commands);
	}

	private void run() {
		LOGGER.info("e-paper thread started");
		do {
			try {
				Commands commands = commandsQueue.take();
				if (commands != null && commands.getCommands() != null) {
					for (Command command : commands.getCommands()) {
						try {
							command.execute(ePaperClient);
						} catch (Exception e) {
							LOGGER.error("Exception while executing command", e);
						}
					}
				}
			} catch (InterruptedException e) {
				stop = true;
			}
		} while (!stop);
		LOGGER.info("e-paper thread stopped");
	}
}
