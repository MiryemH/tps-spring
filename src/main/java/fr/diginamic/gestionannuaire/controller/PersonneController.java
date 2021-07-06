/**
 * 
 */
package fr.diginamic.gestionannuaire.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.diginamic.gestionannuaire.entities.Personne;
import fr.diginamic.gestionannuaire.service.PersonneService;


/**
 * @author Miryem HRARTI
 *
 */
@RestController
public class PersonneController {
	
	@Autowired
	private PersonneService personneService;
	
	
	
	@GetMapping("personnes")
	public List<Personne> getListeDeTigres() {			
		return personneService.getListe();		
	}
	/**
	 * 
	 * @param nvPersonne à ajouter dans la BDD
	 */
	@PostMapping("personnes")
	public String ajouterPersonne(@RequestBody Personne nvPersonne) {		
		return personneService.inserer(nvPersonne);
		
	}
	/**
	 * Supprimer la personne repérée par son Id
	 * @param id de la personne à supprimer
	 */
	@DeleteMapping("personnes/{id}")
	public String supprimerPersonne(@PathVariable("id") Long id) {
		return personneService.supprimer(id);
	}
	
	/**
	 * modifie les infos de la personne repérée par son Id
	 * @param id de la personne à modifier
	 * @param nvPersonne nouvelle personne
	 */
	@PutMapping("/personnes/{id}")  	
	public String  mettreAJourPersonne(@PathVariable("id") long id,@RequestBody Personne nvPersonne) {
		return personneService.mettreAJour(id,nvPersonne);
	}
	
	/**
	 * 
	 * @param id de la personne à chercher
	 * @return la chaine de caractères contenant les infos de la personne repérée par son id si elle existe sinon un message d'erreur
	 */
	@GetMapping("personnes/{id}")
	public String getElement(@PathVariable("id") Long id){
		System.out.println(personneService);
		
		Optional<Personne> personneCherchee =personneService.getById(id);
		if(personneCherchee.isPresent()) {
			return "Personne trouvée: "+personneCherchee.get().toString();
		}
		return "Aucune personne ne correspond à l'ID: "+ id;
	}
}
