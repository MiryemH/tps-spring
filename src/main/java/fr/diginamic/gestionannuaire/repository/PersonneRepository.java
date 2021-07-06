/**
 * 
 */
package fr.diginamic.gestionannuaire.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import fr.diginamic.gestionannuaire.entities.Personne;

/**
 * @author Miryem HRARTI
 *
 */
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
	Optional<Personne> findByNomAndPrenom(String nom, String prenom);
	
}
