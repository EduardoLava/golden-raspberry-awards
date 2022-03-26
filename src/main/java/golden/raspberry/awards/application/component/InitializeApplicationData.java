package golden.raspberry.awards.application.component;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import golden.raspberry.awards.infrastructure.csv.CsvReader;

/**
 * 
 * @author Eduardo
 * 
 * Tarefas de execução na inicialização do sistema
 *
 */
@Component
public class InitializeApplicationData {

	@Autowired
	private CsvReader csvReader;
	
	/**
	 * Executa a leitura do csv e importa os dados quando a aplicação iniciar
	 * 
	 * @throws IllegalStateException
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	@PostConstruct
	public void processCsvData() throws IllegalStateException, FileNotFoundException, URISyntaxException {
		csvReader.importMoviesData();
	}
	
}
