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
public class AdresseService {
	
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private PersonneRepository personneRepository;
	
	/**
	 * 
	 * @return une liste des adresses depuis la BDD
	 * 
	*/
	public List<Adresse> getListe() {		
		return adresseRepository.findAll();
	}
	
	/**
	 * 
	 * @param nvAdresse la nouvelle adresse à insérer dans la BDD
	 */
	public void inserer(Adresse nvAdresse) {
		if(nvAdresse != null) {
			for(Personne pers : nvAdresse.getPersonnes()) {
				personneRepository.save(pers);
			}
		
			adresseRepository.save(nvAdresse);
			System.out.println("Ajout réussi du "+ nvAdresse);
		}
	}
	
	/**
	 * Chercher une adresse par son Id
	 * @param id de l'adresse à chercher
	 * @return adresse ayant l'Id ou un Optional vide si l'adresse non trouvée
	 */
	public Optional<Adresse> getById(long id){
		return adresseRepository.findById(id);
	}
	/**
	 * Supprimer l'adresse ayant id
	 * @param id de l'adresse à supprimer
	 */
	public void supprimer(Long id) {
		
		Optional <Adresse> adresseASupprimer = getById(id);
		if(adresseASupprimer.isEmpty())
			System.out.println("Pas d'adresse ayant cet identifiant: " + id);
		else {
			adresseRepository.delete(adresseASupprimer.get());
			System.out.println(adresseASupprimer+ "vient d'être supprimée de la BDD");
		}
			
		
	}
	/**
	 * modifie l'adresse repérée par son Id
	 * @param id de l'adresse à modifier
	 * @param nvAdresse la nouvelle adresse
	 */
	public void mettreAJour(long id, Adresse nvAdresse) {
		
		Optional<Adresse> adresseAmodifier = getById(id);
		if(adresseAmodifier.isEmpty())
			System.out.println("Aucune adresse ayant l'id:"+id);
		else {
			Adresse adr = adresseAmodifier.get();
			adr.setNumero(nvAdresse.getNumero());
			adr.setRue(nvAdresse.getRue());
			adr.setCodePostal(nvAdresse.getCodePostal());
			adr.setVille(nvAdresse.getVille());
			System.out.println(adr+ " vient d'être mise à jour dans la BDD");
		}
		
	}

}
