package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bll.StatusService;
import bll.model.Status;

@RestController
public class StatusController {
	
	private StatusService _statusService;
	
	public StatusController() {
		_statusService = new StatusService();
	}
	
	@RequestMapping("/status")
	public Status status(){
		return _statusService.getCurrentStatus();
	}
}
