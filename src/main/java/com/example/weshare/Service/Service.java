package com.example.weshare.Service;

import com.example.weshare.Model.SharePool;
import com.example.weshare.Model.User;
import org.springframework.web.context.request.WebRequest;

import java.awt.*;
import java.util.Optional;

public class Service {

    public User createNewUser(WebRequest req){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        return new User(username,password);

    }

    public User findUser(WebRequest req){

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        return new User(username,password);

    }

}
