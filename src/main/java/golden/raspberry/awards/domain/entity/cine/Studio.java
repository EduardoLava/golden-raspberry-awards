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
 * Estúdio de cinema
 *
 */
@Entity
@Table
public class Studio extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8870713172598872649L;
	
	//------------------------------------------------
	//--------------  attributes  --------------------
	//------------------------------------------------

	// Nome do estúdio
	@NotBlank
	@Column(nullable = false, unique = true)
	private String name;

	@ManyToMany(
		fetch = FetchType.LAZY,
		mappedBy = "studios"
	)
	private List<Movie> movies;
	
	//------------------------------------------------
	//--------------  Constructors  --------------------
	//------------------------------------------------
	
	/**
	 * Construtor padrão
	 */
	public Studio() {
		super();
	}
	
	/**
	 * @param name
	 */
	public Studio(@NotBlank String name) {
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

	@Override
	public String toString() {
		return "Studio [name=" + name + ", getId()=" + getId() + "]";
	}

}
