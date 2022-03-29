package golden.raspberry.awards.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import golden.raspberry.awards.GoldenRaspberryAwardsApplication;
import golden.raspberry.awards.application.restful.cine.award.AwardController;
import golden.raspberry.awards.domain.dto.cine.award.AwardIntevalDto;
import golden.raspberry.awards.domain.repository.cine.ProducerRepository;

@SpringBootTest(classes = GoldenRaspberryAwardsApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AwardIntegrationTest {

	private static final String BASE_URI = "/api/awards"; 
	private static final String BASE_URI_MIN_MAX = "max-min-intervals"; 
	
	@Autowired
	private AwardController awardController;
	
	@Autowired
	ProducerRepository producerRepository;
	
	private ObjectMapper objectMapper;
	private MockMvc mockMvc;
	
	@BeforeAll
	void init() {
		
		mockMvc = MockMvcBuilders
			.standaloneSetup(awardController)
			.build();
		
		objectMapper = new ObjectMapper();
		
	}
	
	/**
	 * Valida a listagem de vencedores com menor e maior intervalo entre os premios
	 * 
	 * @throws Exception 
	 */
	@Test
	void validateMaxAndMinIntervalsAwardsTest_returnStatusCode200() throws Exception {
		
		final Long minInterval = producerRepository.findMinInterval();
		final Long maxInterval = producerRepository.findMaxInterval();
		
		mockMvc.perform(get(BASE_URI+"/"+BASE_URI_MIN_MAX))
		.andDo(print())
		.andExpect(status().isOk())
		
		.andExpect(jsonPath("$.min").exists())
		.andExpect(jsonPath("$.min[0].producer").isNotEmpty())
		.andExpect(jsonPath("$.min[0].previousWin").isNotEmpty())
		.andExpect(jsonPath("$.min[0].followingWin").isNotEmpty())
		.andExpect(jsonPath("$.min[0].interval").value(minInterval))
		
		.andExpect(jsonPath("$.max").exists())
		.andExpect(jsonPath("$.max[0].interval").value(maxInterval))
		.andExpect(jsonPath("$.max[0].producer").isNotEmpty())
		.andExpect(jsonPath("$.max[0].previousWin").isNotEmpty())
		.andExpect(jsonPath("$.max[0].followingWin").isNotEmpty())
		;
		
	}
	
	/**
	 * Valida o calculo do intervalo através da subtração dos valores dos campos followingWin e previousWin
	 * 
	 * @throws Exception 
	 */
	@Test
	void validateMinIntervalCalcTest_mustPass() throws Exception {
		
		AwardIntevalDto awardIntevalDto = callApiAndGetResponseContent();
		
		assertThat(awardIntevalDto).isNotNull();
		assertThat(awardIntevalDto.getMin()).isNotNull().isNotEmpty();

		awardIntevalDto.getMin().forEach(min ->{
			assertThat(min.getInterval()).isEqualTo( min.getFollowingWin() - min.getPreviousWin());
		});
		
	}
	
	/**
	 * Valida o calculo do intervalo através da subtração dos valores dos campos followingWin e previousWin
	 * 
	 * @throws Exception 
	 */
	@Test
	void validateMaxIntervalCalcTest_mustPass() throws Exception {
		
		AwardIntevalDto awardIntervalDto = callApiAndGetResponseContent();
		
		assertThat(awardIntervalDto).isNotNull();
		assertThat(awardIntervalDto.getMax()).isNotNull().isNotEmpty();
		
		awardIntervalDto.getMax().forEach(max ->{
			assertThat(max.getInterval()).isEqualTo( max.getFollowingWin() - max.getPreviousWin());
		});
		
	}
	
	/**
	 * Invoca o método da api de busca do maior e menor intervalo entre os premios
	 * @return
	 * @throws Exception
	 */
	private AwardIntevalDto callApiAndGetResponseContent() throws Exception {
		
		String json = mockMvc.perform(get(BASE_URI+"/"+BASE_URI_MIN_MAX))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		;
		
		return convertToDto(json);
		
	}
	
	/**
	 * Converte o json para o objeto de retorno
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	private AwardIntevalDto convertToDto(String json) throws Exception {
		return objectMapper.readValue(json, AwardIntevalDto.class);
	}
	
}
