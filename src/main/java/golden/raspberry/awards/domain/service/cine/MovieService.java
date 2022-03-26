package golden.raspberry.awards.domain.service.cine;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import golden.raspberry.awards.domain.entity.cine.Movie;
import golden.raspberry.awards.domain.entity.cine.Producer;
import golden.raspberry.awards.domain.entity.cine.Studio;
import golden.raspberry.awards.domain.repository.cine.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private StudioService studioService;
	
	@Transactional
	public Movie insert(Movie toSave) {
		
		List<Studio> studios = toSave.getStudios()
		.stream()
		.filter(studio -> studio.getName() != null && !studio.getName().trim().isEmpty())
		.map(studio -> 
			studioService.findStudioByName(studio.getName()).orElse(studio)
		).collect(Collectors.toList());
		
		toSave.setStudios(studios);
		
		List<Producer> producers = toSave.getProducers()
		.stream()
		.filter(producer -> producer.getName() != null && !producer.getName().trim().isEmpty())
		.map(producer -> 
			producerService.findProducerByName(producer.getName()).orElse(producer)
		).collect(Collectors.toList());
		
		toSave.setProducers(producers);
		
		return movieRepository.save(toSave);
	}
	
	public List<Movie> listAll(){
		return movieRepository.findAll();
	}

}
