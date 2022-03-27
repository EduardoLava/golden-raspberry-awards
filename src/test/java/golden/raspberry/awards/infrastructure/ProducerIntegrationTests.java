package golden.raspberry.awards.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import golden.raspberry.awards.domain.entity.cine.Producer;
import golden.raspberry.awards.domain.service.cine.ProducerService;

@SpringBootTest
class ProducerIntegrationTests {

	@Autowired
	ProducerService producerService;
	
	/**
	 * Valida a busca de produtores com premios
	 */
	@Test
	void validateProducersWinnersMustPass() {
		
		List<Producer> onlyWinners = producerService.listOnlyWinners();
		
		assertThat(onlyWinners).isNotNull().isNotEmpty();
		
	}
	
}
