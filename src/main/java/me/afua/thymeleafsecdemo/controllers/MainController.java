package me.afua.thymeleafsecdemo.controllers;

import me.afua.thymeleafsecdemo.UserService;
import me.afua.thymeleafsecdemo.entities.Room;
import me.afua.thymeleafsecdemo.entities.UserData;
import me.afua.thymeleafsecdemo.repositories.RoleRepository;
import me.afua.thymeleafsecdemo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    RoomRepository roomRepository;



    @RequestMapping("/")
    public String showMainPage(Principal p) {

        return "index";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/update/{id}")
    public String updateRoom (@PathVariable("id") long id, Model model){
        model.addAttribute("room", roomRepository.findOne(id));
        return "updateform";
    }

    @PostMapping ("/process")
    public String processForm (@Valid Room room, BindingResult result){
        System.out.println(result);
        if (result.hasErrors()){
            return "roomform";
        }
        roomRepository.save(room);
        return "dashboard";
    }

    @RequestMapping("/detail/{id}")
    public String displayRoom (@PathVariable ("id") long id, Model model){
        model.addAttribute("room", roomRepository.findOne(id));
        return "roomlist";
    }
    @GetMapping("/register")
    public String showRegistrationPage(Model model)
    {
        model.addAttribute("user",new UserData());
        model.addAttribute("pagenumber","4");
        return "registration";
    }


    @GetMapping("/addlisting")
    public String roomForm (Model model){
        model.addAttribute("room", new Room());
        return "roomform";
    }
    @RequestMapping("/roomlisting/{id}")
    public String showlisting (@PathVariable ("id") long id, Model model)
    {
        model.addAttribute("room", roomRepository.findOne(id));
        model.addAttribute("pagenumber","2");
        return "roomlist";
    }

    @RequestMapping("/dashboard")
    public String showPageThree(Model model)
    {
        model.addAttribute("title","dashboard");
        model.addAttribute("pagenumber","3");
        return "dashboard";
    }

}
