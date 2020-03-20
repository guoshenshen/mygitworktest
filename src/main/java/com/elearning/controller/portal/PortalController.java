package com.elearning.controller.portal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class PortalController {

    @RequestMapping("/index")
    public String toPortal(Model model){
        return "portal/index";
    }
}
