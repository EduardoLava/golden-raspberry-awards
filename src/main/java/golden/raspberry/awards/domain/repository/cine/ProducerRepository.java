package golden.raspberry.awards.domain.repository.cine;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import golden.raspberry.awards.domain.entity.cine.Producer;

/**
 * 
 * @author Eduardo
 *
 */
public interface ProducerRepository extends JpaRepository<Producer, Long>{

	/**
	 * Monta sql para carregar produtores de acordo com o nome
	 * 
	 * @param name
	 * @return
	 */
	Optional<Producer> findProducerByName(String name);
	
	/**
	 * Monta sql para carregar os produtores com mais de um premio
	 * 
	 * @return
	 */
	@EntityGraph(attributePaths = {
		"movies"
	})
	@Query(
		" from Producer producer"
		+ " join producer.movies movie "
		+ "where "
		+ " movie.winner = true "
		+ " and exists ("
		+ "  select "
		+ "   producer2 "
		+ "  from "
		+ "   Producer producer2"
		+ "   join producer2.movies movie2 "
		+ "  where "
		+ "   movie2.winner = true "
		+ "   and producer2.id = producer.id "
		+ "   and movie2.id <> movie.id "
		+ ") "
		+ "order by "
		+ " producer.id, "
		+ " movie.year "
	)
	List<Producer> findProducerWithMoreOneWins();
	
}
