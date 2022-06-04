package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Materiale;
import it.uniroma3.siw.repository.MaterialeRepository;

@Service
public class MaterialeService {
	
	@Autowired
	private MaterialeRepository materialeRepository;
	
	@Transactional
	public void save(Materiale mat) {
		this.materialeRepository.save(mat);
	}
	
	public Materiale findById(Long id) {
		return this.materialeRepository.findById(id).get();
	}
	
	public List<Materiale> findAll() {
		List<Materiale> matList = new ArrayList<>();
		
		for(Materiale mat : this.materialeRepository.findAll())
			matList.add(mat);
		
		return matList;
	}
	
	public void deleteById(Long id) {
		this.materialeRepository.deleteById(id);
	}
}
