package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Maglietta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	private String descrizione;
	
	@NotBlank
	private String colore;
	
	@Min(1)
	private float prezzo;
	
	@ManyToMany
	private List<Materiale> materiali;

	@ManyToMany(mappedBy = "magliette")
	private List<Collezione> collezioni;
		
	public Maglietta(@NotBlank String nome, String descrizione, @NotBlank String colore, @Min(1) float prezzo) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.colore = colore;
		this.prezzo = prezzo;
	}
	
	
	public Maglietta() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public List<Materiale> getMateriali() {
		return materiali;
	}

	public void setMateriali(List<Materiale> materiali) {
		this.materiali = materiali;
	}

	public List<Collezione> getCollezioni() {
		return collezioni;
	}

	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
	}
}
