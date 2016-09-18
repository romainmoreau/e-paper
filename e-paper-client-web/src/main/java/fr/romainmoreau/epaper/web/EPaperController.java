package fr.romainmoreau.epaper.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.romainmoreau.epaper.web.api.Commands;

@Controller
@RequestMapping("/")
public class EPaperController {
	@Autowired
	private EPaperWorker ePaperWorker;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody boolean post(@RequestBody Commands commands) {
		return ePaperWorker.offer(commands);
	}
}
