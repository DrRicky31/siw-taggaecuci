package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.service.MagliettaService;

@Controller
public class MagliettaController {

	@Autowired
	private MagliettaService magliettaService;	
	
	@GetMapping("/elencoMagliette")
	public String getMagliette(Model model) {
		model.addAttribute("maglietteList", this.magliettaService.findAll());
		return "/maglietta/elencoMagliette.html";
	}
	
	@GetMapping("/maglietta/{id}")
	public String getMaglietta(@PathVariable Long id, Model model) {
		
		Maglietta maglietta = this.magliettaService.findById(id);
		model.addAttribute("maglietta", maglietta);
		
		return "/maglietta/maglietta.html";
	}
}
