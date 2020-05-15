package com.mir00r.userlogin.users.LoginController;

import com.mir00r.userlogin.Utils.Constant;
import com.mir00r.userlogin.users.models.entites.User;
import com.mir00r.userlogin.users.services.UserService;
import com.mir00r.userlogin.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author mir00r on 15/5/20
 * @project IntelliJ IDEA
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        userValidator.validate(user, bindingResult);

        if (!bindingResult.hasErrors()) {
            userService.save(user);
            modelAndView.addObject("successMessage", "Registration successful.");
            modelAndView.addObject("user", new User());
        }
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("403");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();

        int adminCount = 0;
        int userCount = 0;
        modelAndView.addObject("adminCount", adminCount);//Authentication for NavBar
        modelAndView.addObject("userCount", userCount);//Authentication for NavBar
        //-----------------------------------------
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loginUser = userService.findByEmail(auth.getName());
//        User loginUser = userService.findByUsername(auth.getName());
        modelAndView.addObject(Constant.ATTRIBUTE_NAME.control.getName(), loginUser.getName());//Authentication for NavBar
        modelAndView.addObject(Constant.ATTRIBUTE_NAME.auth.getName(), loginUser);

        modelAndView.setViewName("home");
        return modelAndView;
    }
}
