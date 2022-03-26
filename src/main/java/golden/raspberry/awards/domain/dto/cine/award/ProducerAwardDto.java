package golden.raspberry.awards.domain.dto.cine.award;

/**
 * 
 * @author Eduardo
 * Dto para listagem dos premios
 * 
 * 
 */
public class ProducerAwardDto {

	//------------------------------------------------
	//--------------  attributes  --------------------
	//------------------------------------------------
	
	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;

	
	//------------------------------------------------
	//--------------  Constructors  --------------------
	//------------------------------------------------
	
	/**
	 * @param producer
	 * @param previousWin
	 * @param followingWin
	 * @param interval
	 */
	public ProducerAwardDto(String producer, Integer previousWin, Integer followingWin) {
		super();
		this.producer = producer;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
		this.interval = followingWin - previousWin;
	}
	
	//------------------------------------------------
	//--------------  behaviors  --------------------
	//------------------------------------------------
	
	/**
	 * 
	 */
	public ProducerAwardDto() {
	}
	/**
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}
	/**
	 * @param producer the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}
	/**
	 * @return the previousWin
	 */
	public Integer getPreviousWin() {
		return previousWin;
	}
	/**
	 * @param previousWin the previousWin to set
	 */
	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}
	/**
	 * @return the followingWin
	 */
	public Integer getFollowingWin() {
		return followingWin;
	}
	/**
	 * @param followingWin the followingWin to set
	 */
	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}
	/**
	 * @return the interval
	 */
	public Integer getInterval() {
		return interval;
	}
	/**
	 * @param interval the interval to set
	 */
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
}
