package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ordine;
import it.uniroma3.siw.repository.OrdineRepository;

@Service
public class OrdineService {
	
	@Autowired
	private OrdineRepository ordineRepository;
	
	@Transactional
	public void save(Ordine mat) {
		this.ordineRepository.save(mat);
	}
	
	public Ordine findById(Long id) {
		return this.ordineRepository.findById(id).get();
	}
	
	public List<Ordine> findAll() {
		List<Ordine> ordList = new ArrayList<>();
		
		for(Ordine ord : this.ordineRepository.findAll())
			ordList.add(ord);
		
		return ordList;
	}
	
	public void deleteById(Long id) {
		this.ordineRepository.deleteById(id);
	}
	
	public List<Ordine> findByEmail(String email) {
		return this.ordineRepository.findByEmail(email);
	}
}
