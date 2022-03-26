package golden.raspberry.awards.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

/**
 * @author Eduardo
 * 
 * Entidade com atributos básicos para qualquer entidade existente no projeto
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7164636963046862441L;

	//------------------------------------------------
	//--------------  attributes  --------------------
	//------------------------------------------------
	
	/**
	 * Identificador da entidade
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //atribuido pelo provedor de persistência
	private Long id;
	
	// Data de criação do registro no banco de dados
	@NotNull
	@Column(nullable = false)
	private LocalDateTime created;

	
	// Data de ultima alteração
	private LocalDateTime updated;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getCreated() {
		return created;
	}


	public void setCreated(LocalDateTime created) {
		this.created = created;
	}


	public LocalDateTime getUpdated() {
		return updated;
	}


	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	
	@PrePersist
	protected void onInsert() {
		created = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updated = LocalDateTime.now();
	}
	
}
