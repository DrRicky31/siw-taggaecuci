package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.service.AccessorioService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MagliettaService;
import it.uniroma3.siw.service.OrdineService;

@Controller
public class OrdineController {

	@Autowired
	private OrdineService ordineService;

	@Autowired
	private MagliettaService magliettaService;

	@Autowired
	private AccessorioService accessorioService;

	@Autowired
	private CredentialsService credentialsService;

	@GetMapping("/elencoOrdiniAdmin")
	public String getOrdini(Model model) {
		model.addAttribute("ordiniList", this.ordineService.findAll());
		return "/ordine/elencoOrdini.html";
	}

	@GetMapping("/ordineForm")
	public String showOrdineForm(Model model) {
		Ordine ordine = new Ordine();
		model.addAttribute("ordine", ordine);
		model.addAttribute("magliette", magliettaService.findAll());
		model.addAttribute("accessori", accessorioService.findAll());

		return "/ordine/ordineForm.html";
	}

	@PostMapping("/ordine")
	public String addOrdine(@Valid @ModelAttribute("ordine") Ordine ordine, BindingResult bindingResult, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());

		ordine.setEmail(credentials.getUser().getEmail());
		ordineService.save(ordine);
		model.addAttribute("ordine", ordine);
		return "/ordine/ordine.html";

	}
}
