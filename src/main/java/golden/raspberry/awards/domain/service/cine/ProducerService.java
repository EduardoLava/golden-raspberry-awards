package golden.raspberry.awards.domain.service.cine;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import golden.raspberry.awards.domain.entity.cine.Producer;
import golden.raspberry.awards.domain.repository.cine.ProducerRepository;

/**
 * 
 * @author Eduardo
 * 
 * Serviço com as regras de negócio para tratamento dos produtores de cinema
 *
 */
@Service
public class ProducerService {

	@Autowired
	private ProducerRepository producerRepository;
	
	@Transactional
	public Producer insert(Producer toSave) {
		return producerRepository.save(toSave);
	}


	public Optional<Producer> findProducerByName(@NotBlank final String name) {
		return producerRepository.findProducerByName(name);
	}


	public List<Producer> listAll() {
		return producerRepository.findAll();
	}
	
	public List<Producer> listOnlyWinners(){
		return producerRepository.findProducerWithMoreOneWins();
	}
	
}
