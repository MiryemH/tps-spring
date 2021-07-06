/**
 * 
 */
package fr.diginamic.gestionannuaire.entities;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Miryem HRARTI
 *
 */
@Entity
@Table(name="Personnes")
public class Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	
	
	@ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_adr")	
	private Adresse adresse;

	/**
	 * Constructeur par défaut
	 */
	public Personne() {
		
	}
	/**
	 * Constructeur avec arguments
	 * @param nom
	 * @param prenom
	 */
	public Personne(String nom, String prenom, Adresse adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	/**
	 * @return l'id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * modifie l'id de la personne
	 * @param id le nouveau id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * modifie le nom de la personne
	 * @param nom le nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return le prénom de la personne
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * modifie le prénom de la personne
	 * @param prenom le nouveau prénom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return l'adresse de la personne
	 */
	public Adresse getAdresse() {
		return adresse;
	}
	/**
	 * modifie l'adresse de la personne
	 * @param adresse la nouvelle adresse
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
            Personne autrePersonne = (Personne) autre;
            return Objects.equals(id, autrePersonne.id) && Objects.equals(nom, autrePersonne.nom)
            		&& Objects.equals(prenom, autrePersonne.prenom) && Objects.equals(adresse, autrePersonne.adresse) ;
        }
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(id) + (int)(nom.length()+prenom.length());
    }
    
	/**
	 * @return une chaine de caractères qui décrit les infos de la personne: nom, prénom et adresse
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName()+"{ID: " + this.id + ", " + this.nom.toLowerCase() + " " + this.prenom + ". J'habite à: " + this.adresse + "}";
	}
}
