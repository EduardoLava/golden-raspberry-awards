package golden.raspberry.awards.domain.dto.csv;

import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvNumber;

/**
 * Utilizado para fazer leitura do arquivo csv
 * 
 * @author Eduardo
 *
 */
public class MovieCsvDto {

	//------------------------------------------------
	//--------------  attributes  --------------------
	//------------------------------------------------
	
	@CsvBindByPosition(position = 0, required = true )
	@CsvNumber("0000")
	private Integer year;
	
	@CsvBindByPosition(position = 1, required = true)
	private String title;
	
	@CsvBindAndSplitByPosition(
		position = 2, 
		required = true, 
		elementType = String.class,
		splitOn = "\\s*,\\s*|(?i)\\s*and\\s*"
	)
	private List<String> studios;
	
	@CsvBindAndSplitByPosition(
		position = 3, 
		required = true, 
		elementType = String.class,
		splitOn = "\\s*,\\s*|(?i)\\s*and\\s*"
	)
	private List<String> producers;
	
	@CsvBindByPosition(position = 4)
	private Boolean winner;

	//------------------------------------------------
	//--------------  constructors  --------------------
	//------------------------------------------------
	
	/**
	 * 
	 */
	public MovieCsvDto() {
		super();
		this.winner = false;
	}

	// -------------------------------------------------
	// -----------------  behaviors---------------------
	// -------------------------------------------------
	
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the studios
	 */
	public List<String> getStudios() {
		return studios;
	}

	/**
	 * @param studios the studios to set
	 */
	public void setStudios(List<String> studios) {
		this.studios = studios;
	}

	/**
	 * @return the producers
	 */
	public List<String> getProducers() {
		return producers;
	}

	/**
	 * @param producers the producers to set
	 */
	public void setProducers(List<String> producers) {
		this.producers = producers;
	}

	/**
	 * @return the winner
	 */
	public Boolean getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "MovieCsvDto [year=" + year + ", title=" + title + ", studios=" + studios + ", producers=" + producers
				+ ", winner=" + winner + "]";
	}
	
}
