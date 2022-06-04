package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@ManyToMany
	private List<Maglietta> magliette;
	
	@ManyToMany
	private List<Accessorio> accessori;


	public Ordine(@NotBlank String email, @NotBlank String nome) {
		super();
		this.email = email;
		this.nome = nome;
	}
	
	public Ordine() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Maglietta> getMagliette() {
		return magliette;
	}

	public void setMagliette(List<Maglietta> magliette) {
		this.magliette = magliette;
	}

	public List<Accessorio> getAccessori() {
		return accessori;
	}

	public void setAccessori(List<Accessorio> accessori) {
		this.accessori = accessori;
	}
	
	
}
