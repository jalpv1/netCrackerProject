package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Achievement;
import ua.com.nc.nctrainingproject.models.AchivementDto;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.services.AchivementService;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/achievement")
public class AchievementController {

	private final AchivementService achivementService;

	@Autowired
	public AchievementController(AchivementService achivementService) {
		this.achivementService = achivementService;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Achievement createAchievement
			(@RequestBody Achievement achievement) {

		achivementService.createAchevement(achievement);
		return achievement;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<AchivementDto> getAll() {

		return achivementService.getAllAchievementDtos();
	}

	@RequestMapping(value = "/all-for-user", method = RequestMethod.POST)
	@ResponseBody
	public List<AchivementDto> getAllForUser(@RequestBody User user) {
		return achivementService.getAllAchievementDtosForUser(user.getId());
	}
}
