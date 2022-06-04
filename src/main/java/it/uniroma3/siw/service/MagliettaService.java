package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Maglietta;
import it.uniroma3.siw.repository.MagliettaRepository;

@Service
public class MagliettaService {
	
	@Autowired
	private MagliettaRepository magliettaRepository;
	
	@Transactional
	public void save(Maglietta mat) {
		this.magliettaRepository.save(mat);
	}
	
	public Maglietta findById(Long id) {
		return this.magliettaRepository.findById(id).get();
	}
	
	public List<Maglietta> findAll() {
		List<Maglietta> magList = new ArrayList<>();
		
		for(Maglietta mag : this.magliettaRepository.findAll())
			magList.add(mag);
		
		return magList;
	}
	
	public void deleteById(Long id) {
		this.magliettaRepository.deleteById(id);
	}
}
