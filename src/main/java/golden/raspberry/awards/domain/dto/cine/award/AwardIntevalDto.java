package golden.raspberry.awards.domain.dto.cine.award;

import java.util.List;

/**
 * 
 * @author Eduardo
 * 
 * Permite saber quais foram os premios com maior e menor intervalos
 *
 */
public class AwardIntevalDto {

	//------------------------------------------------
	//--------------  attributes  --------------------
	//------------------------------------------------
	
	private List<ProducerAwardDto> min;
	private List<ProducerAwardDto> max;
	
	//------------------------------------------------
	//--------------  Constructors  --------------------
	//------------------------------------------------

	/**
	 * Construtor padrão
	 */
	public AwardIntevalDto() {
		super();
	}
	
	
	/**
	 * @param min
	 * @param max
	 */
	public AwardIntevalDto(
		List<ProducerAwardDto> min,
		List<ProducerAwardDto> max
	) {
		super();
		this.min = min;
		this.max = max;
	}

	//------------------------------------------------
	//--------------  behaviors  --------------------
	//------------------------------------------------
	

	/**
	 * @return the min
	 */
	public List<ProducerAwardDto> getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(List<ProducerAwardDto> min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public List<ProducerAwardDto> getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(List<ProducerAwardDto> max) {
		this.max = max;
	}

}
