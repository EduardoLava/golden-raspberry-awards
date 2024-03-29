package golden.raspberry.awards.domain.service.cine;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	/**
	 * Insere um produtor 
	 *  
	 * @param toSave
	 * @return
	 */
	@Transactional
	public Producer insert(Producer toSave) {
		return producerRepository.save(toSave);
	}

	/**
	 * Carrega produtores por nome 
	 * 
	 * @param name
	 * @return
	 */
	public Optional<Producer> findProducerByName(@NotBlank final String name) {
		return producerRepository.findProducerByName(name);
	}

	/**
	 * Carrega os produtores existentes na base 
	 * juntamente com os que ainda não foram salvos
	 * 
	 * 
	 * @param producers
	 * @return
	 */
	public List<Producer> existingValidation(List<Producer> producers){

		return producers.stream()
			.filter(producer -> 
				producer.getName() != null 
				&& !producer.getName().trim().isEmpty()
			)
			.map(producer ->
				findProducerByName(producer.getName()).orElse(producer)
			)
			.collect(Collectors.toList());
	}
	
	/**
	 * Lista todos os produtores 
	 * 
	 * @return
	 */
	public List<Producer> listAll() {
		return producerRepository.findAll();
	}
	
	/**
	 * Carrega somente os produtores que venceram
	 * 
	 * @return
	 */
	public List<Producer> listOnlyWinners(){
		return producerRepository.findProducerWithMoreOneWins();
	}
	
}
