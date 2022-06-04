package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.model.Materiale;
import it.uniroma3.siw.service.AccessorioService;
import it.uniroma3.siw.service.CollezioneService;
import it.uniroma3.siw.service.MagliettaService;
import it.uniroma3.siw.service.MaterialeService;

@Controller
public class StartupController {
	
	@Autowired
	private MaterialeService materialeService;
	
	@Autowired
	private MagliettaService magliettaService;
	
	@Autowired
	private AccessorioService accessorioService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	
	@GetMapping("/startup")
	public String inizializza() {
		
		Materiale mat= new Materiale("Cotone", "Cotone 100%");
		List<Materiale> matList = new ArrayList<>();
		matList.add(mat);
		materialeService.save(mat);
		
		Maglietta mag1=new Maglietta("bulldog", "Maglietta con bulldog ricamato sopra, solo contorno", "Nero, bianco", (float) 17.50);
		mag1.setCollezioni(new ArrayList<Collezione>());
		mag1.setMateriali(matList);
		
		Maglietta mag2=new Maglietta("girasole", "Maglietta con girasole stilizzato pieno ricamato", "Bianco", (float) 32.99);
		mag2.setCollezioni(new ArrayList<Collezione>());
		mag2.setMateriali(matList);
		
		Maglietta mag3=new Maglietta("pipa", "Maglietta con disegno stilizzato dell'opera di Magritte", "Bianco", (float) 29.99);
		mag3.setCollezioni(new ArrayList<Collezione>());
		mag3.setMateriali(matList);
		
		Maglietta mag4=new Maglietta("Prova", "Maglietta di prova", "Rosso, Bianco, Nero", (float) 1.66);
		mag4.setCollezioni(new ArrayList<Collezione>());
		mag4.setMateriali(matList);
		
		magliettaService.save(mag1);
		magliettaService.save(mag2);
		magliettaService.save(mag3);
		magliettaService.save(mag4);
		
		Accessorio acc1=new Accessorio("bulldog", "pochette con ricamato sopra sagoma di bulldog", (float)15.99);
		acc1.setCollezioni(new ArrayList<Collezione>());
		acc1.setMateriali(matList);
		
		Accessorio acc2=new Accessorio("pochete mare", "pochette da mare con ricamata sopra scritta e stilizzazione barca a vela", (float)15.99);
		acc2.setCollezioni(new ArrayList<Collezione>());
		acc2.setMateriali(matList);
		
		Accessorio acc3=new Accessorio("portachiavi", "portachiavi con scritta ricamata, colori a scelta del cliente", (float)6.99);
		acc3.setCollezioni(new ArrayList<Collezione>());
		acc3.setMateriali(matList);
		
		accessorioService.save(acc1);
		accessorioService.save(acc2);
		accessorioService.save(acc3);
		
		Collezione coll = new Collezione("RiCamo", "Estate 2022");
		List<Maglietta> mags = new ArrayList<>();
		mags.add(mag1);
		mags.add(mag2);
		mags.add(mag3);
		List<Accessorio> accs = new ArrayList<>();
		accs.add(acc2);
		accs.add(acc3);
		coll.setAccessori(accs);
		coll.setMagliette(mags);
		
		collezioneService.save(coll);		
			
		return "index.html";
	}
}
