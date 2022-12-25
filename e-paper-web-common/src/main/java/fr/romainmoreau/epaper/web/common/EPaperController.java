package fr.romainmoreau.epaper.web.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.romainmoreau.epaper.jaxb.api.Commands;

@Controller
@RequestMapping("/")
public class EPaperController {
	@Autowired
	private EPaperWorker ePaperWorker;

	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody boolean post(@RequestBody Commands commands) {
		return ePaperWorker.offer(commands);
	}
}
