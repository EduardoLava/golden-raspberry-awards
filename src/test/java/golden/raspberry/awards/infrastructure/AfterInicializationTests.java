package golden.raspberry.awards.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import golden.raspberry.awards.GoldenRaspberryAwardsApplication;
import golden.raspberry.awards.domain.entity.cine.Movie;
import golden.raspberry.awards.domain.entity.cine.Producer;
import golden.raspberry.awards.domain.entity.cine.Studio;
import golden.raspberry.awards.domain.service.cine.MovieService;
import golden.raspberry.awards.domain.service.cine.ProducerService;
import golden.raspberry.awards.domain.service.cine.StudioService;

@SpringBootTest(classes = GoldenRaspberryAwardsApplication.class)
class AfterInicializationTests {

	@Autowired
	MovieService movieService;
	
	@Autowired
	ProducerService producerService;
	
	@Autowired
	StudioService studioService;
	
	@Test
	void checkCsvImportMoviesTestMustPass() {
		List<Movie> movies = movieService.listAll();
		
		assertThat(movies).isNotNull().isNotEmpty();
	}
	
	@Test
	void checkCsvImportStudiosTestMustPass() {
		List<Studio> studios = studioService.listAll();
		
		assertThat(studios).isNotNull().isNotEmpty();
	}
	
	@Test
	void checkCsvImportProducerTestMustPass() {
		List<Producer> producers = producerService.listAll();
		
		assertThat(producers).isNotNull().isNotEmpty();
	}
	
}
