package golden.raspberry.awards.domain.entity.cine;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import golden.raspberry.awards.domain.entity.BaseEntity;

/**
 * 
 * @author Eduardo
 * 
 * Entidade que representa um filme
 *
 */
@Entity
@Table
public class Movie extends BaseEntity {

	//------------------------------------------------
	//--------------  attributes  --------------------
	//------------------------------------------------
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5205608374383874045L;

	@NotBlank
	@Column(nullable = false)
	private String title;
	
	@NotNull
	@Column(nullable = false)
	private Integer year;
	
	private Boolean winner;

	@NotEmpty
	@ManyToMany(
		fetch = FetchType.LAZY,
		cascade = {CascadeType.ALL}
	)
	@JoinTable()
	private List<Producer> producers;
	
	@NotEmpty
	@ManyToMany(
		fetch = FetchType.LAZY,
		cascade = {CascadeType.ALL}
	)
	@JoinTable()
	private List<Studio> studios;

	//------------------------------------------------
	//--------------  Constructors  --------------------
	//------------------------------------------------
	
	/**
	 * Construtor padrão 
	 */
	public Movie() {
		super();
	}

	/**
	 * @param title
	 * @param year
	 * @param winner
	 * @param producers
	 * @param studios
	 */
	public Movie(
		@NotBlank String title,
		@NotNull Integer year,
		Boolean winner, 
		@NotEmpty List<Producer> producers,
		@NotEmpty List<Studio> studios
	) {
		super();
		this.title = title;
		this.year = year;
		this.winner = winner;
		this.producers = producers;
		this.studios = studios;
	}



	// -------------------------------------------------
	// -----------------  behaviors---------------------
	// -------------------------------------------------
	
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

	/**
	 * @return the producers
	 */
	public List<Producer> getProducers() {
		return producers;
	}

	/**
	 * @param producers the producers to set
	 */
	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	/**
	 * @return the studios
	 */
	public List<Studio> getStudios() {
		return studios;
	}

	/**
	 * @param studios the studios to set
	 */
	public void setStudios(List<Studio> studios) {
		this.studios = studios;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", year=" + year + ", winner=" + winner + ", producers=" + producers
				+ ", studios=" + studios + ", getId()=" + getId() + "]";
	}

	
}