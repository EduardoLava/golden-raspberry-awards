package golden.raspberry.awards.domain.entity.cine;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import golden.raspberry.awards.domain.entity.BaseEntity;


/**
 * 
 * @author Eduardo
 * 
 * Produtor de filmes
 *
 */
@Entity
@Table
public class Producer extends BaseEntity {

	//------------------------------------------------
	//--------------  attributes  --------------------
	//------------------------------------------------
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2898363880460079847L;

	/**
	 * Nome do produtor
	 */
	@NotBlank
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToMany(
		fetch = FetchType.LAZY,
		mappedBy = "producers"
	)
	private List<Movie> movies;
	
	
	//------------------------------------------------
	//--------------  Constructors  --------------------
	//------------------------------------------------
	
	/**
	 * Construtor padrão
	 */
	public Producer() {
		super();
	}
	
	/**
	 * @param name
	 */
	public Producer(@NotBlank String name) {
		super();
		this.name = name;
	}


	// -------------------------------------------------
	// -----------------  behaviors---------------------
	// -------------------------------------------------
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the movies
	 */
	public List<Movie> getMovies() {
		return movies;
	}

	/**
	 * @param movies the movies to set
	 */
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Producer [name=" + name + ", getId()=" + getId() + "]";
	}
	

}