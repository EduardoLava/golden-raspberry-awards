package golden.raspberry.awards.domain.repository.cine;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import golden.raspberry.awards.domain.entity.cine.Studio;

/**
 * 
 * @author Eduardo
 *
 */
public interface StudioRepository extends JpaRepository<Studio, Long>{

	/**
	 * Carrega estúdios de cinema por nome
	 * 
	 * @param name
	 * @return
	 */
	Optional<Studio> findStudioByName(String name);
	
}
