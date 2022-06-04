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

import it.uniroma3.siw.model.Materiale;
import it.uniroma3.siw.service.MaterialeService;

@Controller
public class MaterialeController {

	@Autowired
	private MaterialeService materialeService;	
	
	@GetMapping("/elencoMateriali")
	public String getMateriali(Model model) {
		model.addAttribute("materialiList", this.materialeService.findAll());
		return "/materiale/elencoMateriali.html";
	}
	
	@GetMapping("/materiale/{id}")
	public String getMateriale(@PathVariable("id") Long id, Model model) {
		
		Materiale materiale = materialeService.findById(id);
		model.addAttribute("materiale", materiale);
		
		return "/materiale/materiale.html";
	}
	
	@GetMapping("/materialeForm")
	public String showMaterialeForm(Model model) {
		Materiale materiale = new Materiale();
		model.addAttribute("materiale", materiale);
		
		return "/materiale/materialeForm.html";
	}
	
	@PostMapping("/materiale")
	public String addMateriale(@Valid @ModelAttribute("materiale") Materiale materiale, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			materialeService.save(materiale);
			model.addAttribute("materiale", materiale);
			return "/materiale/materiale.html";
		}
		else
			return "/materiale/materialeForm.html";
	}
}
