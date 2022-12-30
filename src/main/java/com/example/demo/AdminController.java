package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	@GetMapping(value = "/adminHome")
    public String login(Model model){
    	model.addAttribute("data", adminService.readAll());
        return "adminHome";
    }
	
	@GetMapping(value = "/adminUpdate")
    public String update(Model model, Long id){
    	model.addAttribute("data", adminService.read(id));
        return "adminHome";
    }
	
	@PostMapping(value = "/adminDelete")
    public String login(Model model,int id){
		Long idLong=Integer.toUnsignedLong(id);
		adminService.delete(idLong);
		model.addAttribute("data", adminService.readAll());
        return "adminHome";
    }
	
	@PostMapping(value = "/adminEdit")
    public String edit(Model model,@RequestParam Long id, @RequestParam String username, String [] roles ){
		
		Users user=new Users(id,username,String.join(",", roles));
		adminService.Update(user);
		model.addAttribute("data", adminService.readAll());
        return "adminHome";
    }
	
}














