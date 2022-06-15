package spring.boot.springbootmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.boot.springbootmvc.service.StudentService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserOptionCRUDController {
    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String init() {
        return "index";
    }

    @GetMapping("/getOption")
    public String selectOption(@RequestParam("option") int option, HttpServletResponse servletResponse) {
        switch (option) {
            case 1: {
                return "userInformation";
            }
            case 2: {
                return ("userCreation");
            }
            case 3: {
                return "userUpdate";
            }
            case 4: {
                return "redirect:/showAll";
            }
            case 5: {
                return "removeUser";
            }
        }
        servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "invalidPage";
    }
}
