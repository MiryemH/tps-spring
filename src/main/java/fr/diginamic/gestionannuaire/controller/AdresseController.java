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

import fr.diginamic.gestionannuaire.entities.Adresse;
import fr.diginamic.gestionannuaire.service.AdresseService;

/**
 * @author Miryem HRARTI
 *
 */
@RestController
public class AdresseController {
	@Autowired
	private AdresseService adresseService;
	
	@GetMapping("adresses")
	public List<Adresse> getListeDeTigres() {			
		return adresseService.getListe();		
	}
	/**
	 * 
	 * @param id de l'adresse à chercher
	 * @return la chaine de caractères contenant les infos de l'adresse repérée par son id si elle existe sinon un message d'erreur
	 */
	@GetMapping("adresses/{id}")
	public String getElement(@PathVariable("id") Long id){		
		Optional<Adresse> adresseCherchee =adresseService.getById(id);
		if(adresseCherchee.isPresent()) {
			return "Adresse trouvée: "+adresseCherchee.get().toString();
		}
		return "Aucune adresse ne correspond à l'ID: "+ id;
	}
	/**
	 * 
	 * @param nvAdresse à ajouter dans la BDD
	 */
	@PostMapping("adresses")
	public void ajouterAdresse(@RequestBody Adresse nvAdresse) {
		adresseService.inserer(nvAdresse);		
	}
	/**
	 * Supprimer l'adresse repérée par son Id
	 * @param id de l'adresse à supprimer
	 */
	@DeleteMapping("adresses/{id}")
	public void supprimerAdresse(@PathVariable("id") Long id) {
		adresseService.supprimer(id);
	}
	
	/**
	 * modifie les infos de l'adresse repérée par son Id
	 * @param id de l'adresse à modifier
	 * @param nvAdresse nouvelle adresse
	 */
	@PutMapping("/adresses/{id}")  	
	public void mettreAJourPersonne(@PathVariable("id") long id,@RequestBody Adresse nvAdresse) {
		adresseService.mettreAJour(id,nvAdresse);
	}

}
