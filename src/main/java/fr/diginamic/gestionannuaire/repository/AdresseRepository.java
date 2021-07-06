/**
 * 
 */
package fr.diginamic.gestionannuaire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.gestionannuaire.entities.Adresse;

/**
 * @author Miryem HRARTI
 *
 */
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long>{
	Optional<Adresse> findByNumeroAndRueAndCodePostalAndVille(String numero, String rue, String codePostal, String ville);
}
