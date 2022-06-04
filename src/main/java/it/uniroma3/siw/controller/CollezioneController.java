package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.AccessorioService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.MagliettaService;

@Controller
public class CollezioneController {

	@Autowired
	private CollezioneService collezioneService;	
	
	@Autowired
	private MagliettaService magliettaService;
	
	@Autowired
	private AccessorioService accessorioService;
	
	@GetMapping("/elencoCollezioni")
	public String getCollezioni(Model model) {
		model.addAttribute("collezioniList", this.collezioneService.findAll());
		return "/collezione/elencoCollezioni.html";
	}
	
	@GetMapping("/index")
	public String gotoIndex() {
		return "/index.html";
	}

	@GetMapping("/collezioneForm")
	public String showCollezioneForm(Model model) {
		Collezione collezione = new Collezione();
		model.addAttribute("collezione", collezione);
		model.addAttribute("magliette", magliettaService.findAll());
		model.addAttribute("accessori", accessorioService.findAll());
		
		return "/collezione/collezioneForm.html";
	}
	
	@PostMapping("/collezione")
	public String addCollezione(@Valid @ModelAttribute("collezione") Collezione collezione, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			collezioneService.save(collezione);
			model.addAttribute("collezione", collezione);
			return "/collezione/collezione.html";
		}
		else
			return "/collezione/collezioneForm.html";
	}
}
