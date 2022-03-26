package golden.raspberry.awards.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import golden.raspberry.awards.domain.dto.cine.award.AwardIntevalDto;
import golden.raspberry.awards.domain.service.cine.award.AwardService;

@SpringBootTest
class AwardIntegrationTest {

	@Autowired
	AwardService awardService;
	
	/**
	 * Valida a listagem de vencidores com menor e maior intervalo entre os premios
	 */
	@Test
	void validateMaxAndMinIntervalsAwardsTestMustPass() {
		
		AwardIntevalDto maxMinAwardWinners = awardService.listMaxMinAwardWinners();
		
		assertThat(maxMinAwardWinners).isNotNull();
		assertThat(maxMinAwardWinners.getMax()).isNotNull().isNotEmpty();
		assertThat(maxMinAwardWinners.getMin()).isNotNull().isNotEmpty();
		
	}
	
}
