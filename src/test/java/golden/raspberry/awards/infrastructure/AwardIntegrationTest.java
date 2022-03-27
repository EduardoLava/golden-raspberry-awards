package golden.raspberry.awards.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import golden.raspberry.awards.application.restful.cine.award.AwardController;
import golden.raspberry.awards.domain.dto.cine.award.AwardIntevalDto;

@SpringBootTest
class AwardIntegrationTest {

	@Autowired
	AwardController awardController;
	
	/**
	 * Valida a listagem de vencidores com menor e maior intervalo entre os premios
	 */
	@Test
	void validateMaxAndMinIntervalsAwardsTestMustPass() {
		
		AwardIntevalDto maxMinAwardWinners = awardController.findMaxMinAwardsIntervals();
		
		assertThat(maxMinAwardWinners).isNotNull();
		assertThat(maxMinAwardWinners.getMax()).isNotNull().isNotEmpty();
		assertThat(maxMinAwardWinners.getMin()).isNotNull().isNotEmpty();
		
	}
	
}
