package golden.raspberry.awards.domain.repository.cine;

import org.springframework.data.jpa.repository.JpaRepository;

import golden.raspberry.awards.domain.entity.cine.Movie;

/**
 * 
 * @author Eduardo
 *
 */
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
