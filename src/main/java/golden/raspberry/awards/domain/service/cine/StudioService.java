package golden.raspberry.awards.domain.service.cine;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import golden.raspberry.awards.domain.entity.cine.Studio;
import golden.raspberry.awards.domain.repository.cine.StudioRepository;

/**
 * 
 * @author Eduardo
 * 
 * Serviço para tratamento de regras de negócio para estúdios de cinema
 *
 */
@Service
public class StudioService {

	@Autowired
	private StudioRepository studioRepository;
	
	@Transactional
	public Studio insert(@NotNull Studio studio) {
		
		return studioRepository.save(studio);
		
	}
	
	public Optional<Studio> findStudioByName(@NotBlank String name) {
		return studioRepository.findStudioByName(name);
	}

	public List<Studio> listAll() {
		return studioRepository.findAll();
	}
	
}
