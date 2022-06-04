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

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.service.AccessorioService;
import it.uniroma3.siw.service.MaterialeService;

@Controller
public class AccessorioController {

	@Autowired
	private AccessorioService accessorioService;	
	
	@Autowired
	private MaterialeService materialeService;
	
	@GetMapping("/elencoAccessori")
	public String getAccessori(Model model) {
		model.addAttribute("accessoriList", this.accessorioService.findAll());
		return "/accessorio/catalogoAccessori.html";
	}
	
	@GetMapping("/accessorio/{id}")
	public String getAccessorio(@PathVariable Long id, Model model) {
		Accessorio accessorio = this.accessorioService.findById(id);
		model.addAttribute("accessorio", accessorio);
		
		return "/accessorio/accessorio.html";
	}
	
	@GetMapping("/accessorioForm")
	public String showAccessorioForm(Model model) {
		Accessorio accessorio = new Accessorio();
		model.addAttribute("accessorio", accessorio);
		model.addAttribute("materiali", materialeService.findAll());
		
		return "/accessorio/accessorioForm.html";
	}
	
	@PostMapping("/accessorio")
	public String addAccessorio(@Valid @ModelAttribute("accessorio") Accessorio accessorio, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			accessorioService.save(accessorio);
			model.addAttribute("accessorio", accessorio);
			return "/accessorio/accessorio.html";
		}
		else
			model.addAttribute("materiali", materialeService.findAll());
			return "/accessorio/accessorioForm.html";
	}
}
