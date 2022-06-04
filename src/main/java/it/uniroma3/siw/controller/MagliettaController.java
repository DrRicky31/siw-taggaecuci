package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.service.MagliettaService;
import it.uniroma3.siw.service.MaterialeService;

@Controller
public class MagliettaController {

	@Autowired
	private MagliettaService magliettaService;	
	
	@Autowired
	private MaterialeService materialeService;
	
	@GetMapping("/elencoMagliette")
	public String getMagliette(Model model) {
		model.addAttribute("maglietteList", this.magliettaService.findAll());
		return "/maglietta/catalogoMagliette.html";
	}
	
	@GetMapping("/maglietta/{id}")
	public String getMaglietta(@PathVariable Long id, Model model) {
		
		Maglietta maglietta = this.magliettaService.findById(id);
		model.addAttribute("maglietta", maglietta);
		
		return "/maglietta/maglietta.html";
	}
	
	@GetMapping("/magliettaForm")
	public String showMagliettaForm(Model model) {
		Maglietta maglietta = new Maglietta();
		model.addAttribute("maglietta", maglietta);
		model.addAttribute("materiali", materialeService.findAll());
		
		return "/maglietta/magliettaForm.html";
	}
	
	@PostMapping("/maglietta")
	public String addMaglietta(@Valid @ModelAttribute("maglietta") Maglietta maglietta, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			magliettaService.save(maglietta);
			model.addAttribute("maglietta", maglietta);
			return "/maglietta/maglietta.html";
		}
		else
			model.addAttribute("materiali", materialeService.findAll());
			return "/maglietta/magliettaForm.html";
	}
}
