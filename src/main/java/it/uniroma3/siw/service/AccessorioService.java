package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.repository.AccessorioRepository;

@Service
public class AccessorioService {
	
	@Autowired
	private AccessorioRepository accessorioRepository;
	
	@Transactional
	public void save(Accessorio mat) {
		this.accessorioRepository.save(mat);
	}
	
	public Accessorio findById(Long id) {
		return this.accessorioRepository.findById(id).get();
	}
	
	public List<Accessorio> findAll() {
		List<Accessorio> accList = new ArrayList<>();
		
		for(Accessorio acc : this.accessorioRepository.findAll())
			accList.add(acc);
		
		return accList;
	}
	
	public void deleteById(Long id) {
		this.accessorioRepository.deleteById(id);
	}
}
