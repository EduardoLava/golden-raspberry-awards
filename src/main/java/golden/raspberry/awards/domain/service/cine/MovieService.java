package golden.raspberry.awards.domain.service.cine;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import golden.raspberry.awards.domain.entity.cine.Movie;
import golden.raspberry.awards.domain.repository.cine.MovieRepository;

/**
 * 
 * @author Eduardo
 * 
 * Serviço com as regras de negócio para tratamento dos filmes 
 *
 */
@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private StudioService studioService;
	
	/**
	 * Faz a inserção dos filmes e entidade associadas
	 * 
	 * @param toSave
	 * @return
	 */
	@Transactional
	public Movie insert(Movie toSave) {
		
		toSave.setStudios(studioService.existingValidation(toSave.getStudios()));
		toSave.setProducers(producerService.existingValidation(toSave.getProducers()));
		
		return movieRepository.save(toSave);
	}
	
	/**
	 * Lista todos os filmes existentes na base
	 * 
	 * @return
	 */
	public List<Movie> listAll(){
		return movieRepository.findAll();
	}

}
