package golden.raspberry.awards.application.restful.cine.award;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import golden.raspberry.awards.domain.dto.cine.award.AwardIntevalDto;
import golden.raspberry.awards.domain.service.cine.award.AwardService;

@RestController(value = "/api/awards")
public class AwardController {

	@Autowired
	private AwardService awardService;
	
	@GetMapping("max-min-intervals")
	public AwardIntevalDto findMaxMinAwardsIntervals(){

		return awardService.listMaxMinAwardWinners();
		
	}

	
}
