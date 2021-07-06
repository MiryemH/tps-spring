/**
 * 
 */
package fr.diginamic.gestionannuaire.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * @author Miryem HRARTI
 *
 */

@Entity
@Table(name="Adresses")
public class Adresse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_adr", nullable=false)
	public Long id;

	private String numero;
	private String rue;
	
	@Column(name="code_postal")
	private String codePostal;
	private String ville;
	
	@JsonIgnore
	@OneToMany(mappedBy="adresse", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Set<Personne> personnes = new HashSet<>();

	/**
	 * Constructeur par défaut
	 */
	public Adresse() {
	}
	/**
	 * Constructeur avec arguments
	 * @param numero
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Adresse(String numero, String rue, String codePostal, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	/**
	 * 
	 * @return id de l'adresse
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * modifie l'id de l'adresse
	 * @param id nouvau id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return le numéro de la rue/avenue/impasse
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * 
	 * @param numero nouveau numéro de la rue/avenue/impasse
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * 
	 * @return la rue 
	 */
	public String getRue() {
		return rue;
	}
	
	/**
	 * modifie la rue
	 * @param rue nouveau nom de rue
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	/**
	 * 
	 * @return le code postal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	
	/***
	 * modifie le code postal
	 * @param codePostal nouveau code postal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	/**
	 * 
	 * @return la ville
	 */
	public String getVille() {
		return ville;
	}
	
	/**
	 * modifie le nom de la ville
	 * @param ville nouveau nom de la ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	
	/**
	 * @return la liste des personnes habitant cette adresse
	 */
	public Set<Personne> getPersonnes() {
		return personnes;
	}
	/**
	 * modifie la liste des personnes habitant cette adresse
	 * @param personnes nouvelle liste de personnes
	 */
	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}
	
	@Override
    public boolean equals(Object autre){
        if(this == autre)
            return true;
        if(autre == null)
            return false;
        if(this.getClass() != autre.getClass())
            return false;
        else{
            Adresse autreAdresse = (Adresse) autre;
            return this.numero.equals(autreAdresse.getNumero())
                    && this.rue.equals(autreAdresse.getRue())
                    && this.codePostal.equals(autreAdresse.getCodePostal())
                    && this.ville.equals(autreAdresse.getVille());
        }
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(id)+ (int) (this.numero.length() + this.rue.length() + this.ville.length());
    }
	/**
	 * @return une chaine de caractères qui décrit l'adresse postale
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName()+"{numero=" + numero + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "}";
	}	
}
