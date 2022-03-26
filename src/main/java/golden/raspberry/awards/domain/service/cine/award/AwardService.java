package golden.raspberry.awards.domain.service.cine.award;

import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import golden.raspberry.awards.domain.dto.cine.award.AwardIntevalDto;
import golden.raspberry.awards.domain.dto.cine.award.ProducerAwardDto;
import golden.raspberry.awards.domain.entity.cine.Movie;
import golden.raspberry.awards.domain.entity.cine.Producer;
import golden.raspberry.awards.domain.service.cine.ProducerService;

/**
 * 
 * @author Eduardo
 * 
 * Serviço para regras de negócios dos prêmios
 *
 */
@Service
public class AwardService {

	@Autowired
	private ProducerService producerService;
	
	/**
	 * Lista os ganhadores que tiveram premios no menor intervalo registrado 
	 * 	e ganhadores no maior intervalo registrado
	 * @return
	 */
	public AwardIntevalDto listMaxMinAwardWinners(){
		
		List<Producer> producers = producerService.listOnlyWinners();
		
		List<ProducerAwardDto> awards = convertToAward(producers);
		
		return filterBySmallestAndLongestIntervals(awards);
		
	}
	
	/**
	 * Converte todos os filmes dos produtores de cinema
	 * 
	 * @param producers
	 * @return
	 */
	private List<ProducerAwardDto> convertToAward(List<Producer> producers) {
		
		return producers
		.stream()
		.map( this::toProducerAwards)
		.collect(Collectors.toList())
		.stream()
		.flatMap(Collection::stream).collect(Collectors.toList());
		
	}
	
	/**
	 * Retorna uma lista de premios do produtor
	 * 
	 * Os intervalos são calculados sempre com base no proximo ano em que houve filmes premiados
	 * 
	 * @param producer
	 * @return
	 */
	private List<ProducerAwardDto> toProducerAwards(Producer producer) {
		return 
			producer.getMovies()
				.stream()
				.filter(movie -> hasMovieInNextYear(
						producer.getMovies(), 
						movie.getYear()
					)
				)
				.map(movie -> {
					
					OptionalInt nextYearWins = findNextYearMovie(
						producer.getMovies(), 
						movie.getYear()
					);
					
					return new ProducerAwardDto(
						producer.getName(), 
						movie.getYear(),
						nextYearWins.getAsInt()
					);
						
				}).collect(Collectors.toList());
	}
	
	/**
	 * Faz a filtragem da lista geral de premios, para trazer somente os que tiveram o maior intervalo 
	 * e os que tiveram o menor intervalo
	 * 
	 * @param awards
	 * @return
	 */
	private AwardIntevalDto filterBySmallestAndLongestIntervals(List<ProducerAwardDto> awards) {

		List<ProducerAwardDto> largestInterval = filterByInterval(
			awards, 
			findLargestInterval(awards)
		);
		
		List<ProducerAwardDto> samllestInterval = filterByInterval(
			awards, 
			findSmallestInterval(awards)
		);
		
		return new AwardIntevalDto(samllestInterval, largestInterval);
	}
	
	/**
	 * Filtra a lista de acordo com o intervalo definido
	 * 
	 * @param awards
	 * @param interval
	 * @return
	 */
	private List<ProducerAwardDto> filterByInterval(List<ProducerAwardDto> awards, Integer interval) {
		
		return awards
				.stream()
				.filter(award -> award.getInterval().equals(interval))
				.collect(Collectors.toList());
		
	}

	/**
	 * Calcula o maior intervalo entre premiações
	 * 
	 * @param awards
	 * @return
	 */
	private Integer findLargestInterval(List<ProducerAwardDto> awards) {

		return awards.stream()
			.mapToInt(ProducerAwardDto::getInterval)
			.max()
			.getAsInt();
		
	}
	
	/**
	 * Calcula o menor intervalo entre premiações
	 * 
	 * @param awards
	 * @return
	 */
	private Integer findSmallestInterval(List<ProducerAwardDto> awards) {
		
		return awards.stream()
				.mapToInt(ProducerAwardDto::getInterval)
				.min()
				.getAsInt();
		
	}
	

	/**
	 * Verifica se existe um premio no proximo ano 
	 * 
	 * @param movies
	 * @param currentYear
	 * @return
	 */
	private boolean hasMovieInNextYear(List<Movie> movies, Integer currentYear) {
		
		OptionalInt optinal = findNextYearMovie(movies, currentYear);
		
		return optinal.isPresent();
		
	}
	
	/**
	 * Busca o ano do proximo premio
	 * 
	 * @param movies
	 * @param currentYear
	 * @return
	 */
	private OptionalInt findNextYearMovie(List<Movie> movies, Integer currentYear) {
		
		return movies.stream()
				.filter(movie2 -> movie2.getYear() > currentYear)
				.mapToInt(Movie::getYear)
				.min();
	}


}
