package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.CollezioneService;

@Controller
public class CollezioneController {

	@Autowired
	private CollezioneService collezioneService;	
	
	@GetMapping("/elencoCollezioni")
	public String getCollezioni(Model model) {
		model.addAttribute("collezioniList", this.collezioneService.findAll());
		return "/collezione/elencoCollezioni.html";
	}
}
