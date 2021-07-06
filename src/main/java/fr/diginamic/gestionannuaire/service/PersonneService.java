/**
 * 
 */
package fr.diginamic.gestionannuaire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.gestionannuaire.entities.Adresse;
import fr.diginamic.gestionannuaire.entities.Personne;
import fr.diginamic.gestionannuaire.repository.AdresseRepository;
import fr.diginamic.gestionannuaire.repository.PersonneRepository;

/**
 * @author Miryem HRARTI
 *
 */
@Service
public class PersonneService {
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private AdresseRepository adresseRepository;
	
	/**
	 * 
	 * @return une liste des personnes depuis la BDD
	 * 
	*/
	public List<Personne> getListe() {		
		return personneRepository.findAll();
	}
	
	/**
	 * 
	 * @param nvPersonne la nouvelle personne à insérer dans la BDD
	 */
	public String inserer(Personne nvPersonne) {
		
		Optional<Personne> personneExiste = personneRepository.findByNomAndPrenom(nvPersonne.getNom(), nvPersonne.getPrenom());
		Optional<Adresse> adresseExiste = adresseRepository.findByNumeroAndRueAndCodePostalAndVille(
				nvPersonne.getAdresse().getNumero(), nvPersonne.getAdresse().getRue(),  nvPersonne.getAdresse().getCodePostal(), nvPersonne.getAdresse().getVille());

		if(!personneExiste.isEmpty() && personneExiste.get().getAdresse().equals(nvPersonne.getAdresse())) {
			return "Personne existe déjà dans la BDD";
		}
		else  {
			if(!adresseExiste.isEmpty()) {
				nvPersonne.setAdresse(adresseExiste.get());	
			}
			personneRepository.save(nvPersonne);
			return "La personne a été insérée dans la BDD";
		}					
	}
	
	/**
	 * Chercher une personne par son Id
	 * @param id de la personne à chercher
	 * @return personne ayant l'Id ou un Optional vide si la personne est non trouvée
	 */
	public Optional<Personne> getById(long id){	
		return personneRepository.findById(id);		
	}
	/**
	 * Supprimer la personne ayant id
	 * @param id de la personne à supprimer
	 */
	public String supprimer(Long id) {
		
		Optional <Personne> personneASupprimer = getById(id);
		if(personneASupprimer.isEmpty())
			return "Pas de personne ayant cet identifiant: " + id;
		else {
			personneRepository.delete(personneASupprimer.get());
			return personneASupprimer+ "vient d'être supprimée de la BDD";
		}
			
		
	}
	/**
	 * modifie la personne repérée par son Id
	 * @param id de la personne à modifier
	 * @param nvPersonne la nouvelle personne
	 */
	public String mettreAJour(long id, Personne nvPersonne) {
		
		Optional<Personne> personneAmodifier = getById(id);
		if(personneAmodifier.isEmpty())
			return "Aucune personne ayant l'id:"+id;
		else {
			Personne pers = personneAmodifier.get();
			pers.setNom(nvPersonne.getNom());
			pers.setPrenom(nvPersonne.getPrenom());
			pers.setAdresse(nvPersonne.getAdresse());
			return pers+ " vient d'être mise à jour dans la BDD";
		}
		
	}

}
