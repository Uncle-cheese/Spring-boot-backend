package com.app.registration.controller;

import com.app.registration.model.User;
import com.app.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping("/registeruser")
    public User registerUser(@RequestBody User user) throws Exception {
        String tempEmailId =user.getEmailId();

        //check if the email exist
        if(tempEmailId != null && !"".equals((tempEmailId))) {
           User userObj = service.fetchUserByEmailId(tempEmailId);
           if(userObj != null) {
               throw new Exception("user with " + tempEmailId + " is already exist");
           }
        }
        User userObj = null;
        userObj =service.saveUser(user);
        return userObj;
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmailId();
        String tempPassword = user.getPassword();
        User userobj = null;
        if(tempEmailId !=null && tempPassword !=null) {
            userobj = service.fetchUserByEmailIdAndPassword(tempEmailId,tempPassword);
        }
        if(userobj == null) {
            throw new Exception("Bad credentails");
        }
        return userobj;
    }
}
