package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    @Autowired
    MyUserDetailsService userDetailsService;
    
    @GetMapping(value = "/home")
    public String login(Model model){
    	model.addAttribute("messagese", "new data");
        return "home";
    }
    
    @GetMapping(value = "/login")
    public String login2(Model model){
    	model.addAttribute("messagese", "new data");
        return "login2";
    }
    
    @GetMapping("/voltaire")
    public String getMessage(Model model)
    {
        model.addAttribute("name", "John");
        return "login";
    }

    @GetMapping(value = "/test")
    public ModelAndView test(){

        ModelAndView modelAndView=new ModelAndView("login.html");
        return modelAndView;

    }

    @GetMapping(value = "/register")
    public ModelAndView register(){
        ModelAndView modelAndView=new ModelAndView("register.html");
        return modelAndView;
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(String username, String password)  {
    	
       Users user=new Users(username,password);
       ErrorExceptor errorExceptor=new ErrorExceptor();
       
        try {
            userDetailsService.save(user);
        } catch (Exception e) {
            errorExceptor.setError(e.toString());
            ModelAndView modelAndView=new ModelAndView("register");
            modelAndView.addObject("message", errorExceptor.getError());

            return modelAndView;
        }

      
        ModelAndView modelAndView=new ModelAndView("redirect:/login");
        modelAndView.addObject("message", errorExceptor.getError());

       return modelAndView;
    }
}
