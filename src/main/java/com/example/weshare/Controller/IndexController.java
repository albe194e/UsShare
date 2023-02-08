package com.example.weshare.Controller;


import com.example.weshare.Model.SharePool;
import com.example.weshare.Model.User;
import com.example.weshare.Repositories.PoolRepo;
import com.example.weshare.Repositories.UserRepo;
import com.example.weshare.Service.Service;
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
    public String index(HttpSession session) {


        return "/Frontpage";
    }

    @GetMapping("/login")
    public String login(WebRequest req, HttpSession session) {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<User> foundUser = userRepo.findUserByUsernameAndPassword(username, password);


        if (foundUser.isPresent()) {
            session.setAttribute("user", foundUser.get());

            Optional<SharePool> foundPool = poolRepo.findById(foundUser.get().getSharePool().getPoolId());

            session.setAttribute("sharePool", foundPool.get());


            return "/userpage";
        } else {
            return "redirect:/";
        }

    }


    @PostMapping("/user")
    public String createUser(WebRequest req, HttpSession session) {



        User user = service.createNewUser(req);

        userRepo.save(user);


        session.setAttribute("user", user);

        return "redirect:/";
    }

    @PostMapping("update")
    public String updateContribution(WebRequest req, HttpSession session) {

        int contribution = Integer.parseInt(req.getParameter("contribution"));

        User user = (User) session.getAttribute("user");

        user.setContribution(contribution);

        userRepo.save(user);

        return "redirect:/";
    }

    @PostMapping("/createNewPool")
    public String createNewPool(WebRequest req, HttpSession session) {

        SharePool pool = new SharePool();

        poolRepo.save(pool);

        User user = (User) session.getAttribute("user");

        user.setSharePool(pool);

        session.setAttribute("sharePool", pool);

        userRepo.save(user);

        return "/userpage";
    }

    @GetMapping("/poolPage")
    public String poolPage(HttpSession session, WebRequest req) {

        String poolId = req.getParameter("poolId");

        Optional<SharePool> foundPool = poolRepo.findById(Integer.parseInt(poolId));

        foundPool.ifPresent(sharePool -> session.setAttribute("sharePool", sharePool));

        return "/poolPage";
    }

    @PostMapping("/addContribution")
    public String addContribution(HttpSession session) {

        User user = (User) session.getAttribute("user");

        SharePool pool = (SharePool) session.getAttribute("sharePool");

        pool.addToTotalSum(user.getContribution());

        poolRepo.save(pool);

        return "redirect:/poolPage";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
