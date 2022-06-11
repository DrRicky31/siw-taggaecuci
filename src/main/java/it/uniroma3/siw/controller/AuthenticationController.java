package it.uniroma3.siw.controller;

import static it.uniroma3.siw.model.Credentials.ADMIN_ROLE;
import static it.uniroma3.siw.model.Credentials.CLIENT_ROLE;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.OrdineService;

@Controller
public class AuthenticationController {

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private CredentialsValidator credentialsValidator;

	@Autowired
	private OrdineService ordineService;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		List<String> roles = new ArrayList<>();
		roles.add(CLIENT_ROLE);
		roles.add(ADMIN_ROLE);
		model.addAttribute("roles", roles);
		return "registerUser.html";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		return "loginForm.html";
	}

	@GetMapping("/default")
	public String defaultAfterLogin(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		model.addAttribute("user", credentials.getUser());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			model.addAttribute("ordini", this.ordineService.findAll());
			return "/ordine/elencoOrdini.html";
		}
		if (credentials.getRole().equals(Credentials.CLIENT_ROLE)) {
			model.addAttribute("ordini", this.ordineService.findByEmail(credentials.getUser().getEmail()));
			return "/ordine/ordiniUser.html";
		}
		return "index.html";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult userBindingResult,
			@Valid @ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult,
			Model model) {

		userValidator.validate(user, credentialsBindingResult);
		credentialsValidator.validate(credentials, credentialsBindingResult);
		if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			credentials.setUser(user);
			credentialsService.saveCredentials(credentials);
			return "registrationSuccessful.html";
		}
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		List<String> roles = new ArrayList<>();
		roles.add(CLIENT_ROLE);
		roles.add(ADMIN_ROLE);
		model.addAttribute("roles", roles);
		return "registerUser.html";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "index";
	}
}
