package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Transactional
	public void save(Collezione mat) {
		this.collezioneRepository.save(mat);
	}
	
	public Collezione findById(Long id) {
		return this.collezioneRepository.findById(id).get();
	}
	
	public List<Collezione> findAll() {
		List<Collezione> colList = new ArrayList<>();
		
		for(Collezione col : this.collezioneRepository.findAll())
			colList.add(col);
		
		return colList;
	}
	
	public void deleteById(Long id) {
		this.collezioneRepository.deleteById(id);
	}
}
