package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.service.AccessorioService;

@Controller
public class AccessorioController {

	@Autowired
	private AccessorioService accessorioService;	
	
	@GetMapping("/elencoAccessori")
	public String getAccessori(Model model) {
		model.addAttribute("accessoriList", this.accessorioService.findAll());
		return "/accessorio/elencoAccessori.html";
	}
	
	@GetMapping("/accessorio/{id}")
	public String getAccessorio(@PathVariable Long id, Model model) {
		Accessorio accessorio = this.accessorioService.findById(id);
		model.addAttribute("accessorio", accessorio);
		
		return "/accessorio/accessorio.html";
	}
}
