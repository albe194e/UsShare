package com.example.weshare.Controller;


import com.example.weshare.Model.User;
import com.example.weshare.Repositories.PoolRepo;
import com.example.weshare.Repositories.UserRepo;
import com.example.weshare.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@Controller
public class IndexController {

    Service service = new Service();


    @Autowired
    UserRepo userRepo;
    @Autowired
    PoolRepo poolRepo;

    @GetMapping("/")
    public String index(HttpSession session){


        return "/Frontpage";
    }

    @GetMapping("/login")
    public String login(WebRequest req, HttpSession session){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> foundUser = userRepo.findUserByUsernameAndPassword(username,password);

        if (foundUser.isPresent()){
            session.setAttribute("user", foundUser.get());

            return "/userpage";
        } else {
            return "redirect:/";
        }

    }


    @PostMapping("/user")
    public String createUser(WebRequest req, HttpSession session){

        User user = service.createNewUser(req);

        userRepo.save(user);

        session.setAttribute("user", user);

        return "redirect:/";
    }

    @PostMapping("update")
    public String updateContribution(WebRequest req, HttpSession session){

        int contribution = Integer.parseInt(req.getParameter("contribution"));

        User user = (User) session.getAttribute("user");

        user.setContribution(contribution);

        userRepo.save(user);

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }
}
