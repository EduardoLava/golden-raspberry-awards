package golden.raspberry.awards.infrastructure.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;

import golden.raspberry.awards.domain.dto.csv.MovieCsvDto;
import golden.raspberry.awards.domain.entity.cine.Movie;
import golden.raspberry.awards.domain.entity.cine.Producer;
import golden.raspberry.awards.domain.entity.cine.Studio;
import golden.raspberry.awards.domain.service.cine.MovieService;

/**
 * 
 * @author Eduardo 
 * 
 * Responsável por fazer a leitura do arquivo CSV
 *
 */
@Component
public class CsvReader {

	/**
	 * Csv com dados de entrada
	 */
	private static final String CSV_PATH = "csv"+File.separator+"movielist.csv";

	/**
	 * Delimitador de colunas do CSV
	 */
	private static final char CSV_SEPARATOR = ';';
	
	// movie service
	@Autowired
	private MovieService movieService;
	
	private static final Logger logger = Logger.getLogger(CsvReader.class.getName());
	
	/**
	 * Retorna o path do arquivo csv presente na pasta resources do projeto
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	private Path getCsvPath() throws URISyntaxException {
		return Paths.get(
			ClassLoader
				.getSystemResource(CSV_PATH)
				.toURI()
		);
	}
	
	/**
	 * Faz a leitura do arquivo csv contendo dados de entrada
	 * 
	 * @throws URISyntaxException 
	 * @throws FileNotFoundException 
	 * @throws IllegalStateException 
	 */
	public void importMoviesData() throws URISyntaxException, IllegalStateException, FileNotFoundException {
			
		Path csvPath = getCsvPath();
		
		logger.log(Level.INFO, "Path arquivo csv: {0}", csvPath);
		
		List<MovieCsvDto> parseToList = parseToDtoList(csvPath.toFile());

		List<Movie> parseDtoToMovieList = parseDtoToMovieList(parseToList);
		
		persistInDb(parseDtoToMovieList);
			
	}
	
	/**
	 * Faz a leitura de um arquivo csv para importar os dados dos filmes
	 * 
	 * A ordem das colunas deve ser
	 * 
	 * year title studios producers winner
	 * 
	 * Por padrão o delimitador padrão é ';' caso necessário alterar a constante
	 * CSV_DELIMITER
	 * 
	 * @param fileCsv
	 * @return
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 */
	private List<MovieCsvDto> parseToDtoList(File fileCsv) throws IllegalStateException, FileNotFoundException {
		
		List<MovieCsvDto> parse = new CsvToBeanBuilder<MovieCsvDto>(
					new FileReader(fileCsv)
				)
				.withType(MovieCsvDto.class)
				.withSkipLines(1)
		        .withIgnoreLeadingWhiteSpace(true)
				.withSeparator(CSV_SEPARATOR)
				.build()
				.parse();
		
		logger.log(Level.INFO, "Exportação de {0} linhas do CSV para DTO feita com sucesso!", parse.size());
		
		return parse;

	}
	
	/**
	 * Transforma o dto em uma lista de filmes, contendo produtores e estúdios
	 * 
	 * @param movieCsvDtos
	 * @return
	 */
	private List<Movie> parseDtoToMovieList(@NotEmpty List<MovieCsvDto> movieCsvDtos) {
		
		return movieCsvDtos.stream().map(dto ->
			new Movie(
				dto.getTitle(), 
				dto.getYear(),
				dto.getWinner(), 
				toProducerList(dto.getProducers()), 
				toStudioList(dto.getStudios())
			)
		).collect(Collectors.toList());
		
	}
	
	/**
	 * Converte para uma lista de produtores
	 * 
	 * @param producers
	 * @return
	 */
	private List<Producer> toProducerList(@NotEmpty List<String> producers){
		
		return producers
				.stream()
				.map(Producer::new)
				.collect(Collectors.toList());
		
	}
	
	/**
	 * Converte para uma lista de estúdios
	 * @param studios
	 * @return
	 */
	private List<Studio> toStudioList(@NotEmpty List<String> studios) {
		
		return studios
				.stream()
				.map(Studio::new)
				.collect(Collectors.toList());
		
	}
	
	/**
	 * Salva os filmes no banco de dados
	 * 
	 * @param movies
	 */
	private void persistInDb(@NotEmpty List<Movie> movies) {
		
		movies.forEach(movieService::insert);
		
	}
}
