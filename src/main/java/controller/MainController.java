package controller;

/**
 * Created by Arkkienkeli on 18.12.2014.
 */
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import dao.IDisksDao;
import entity.Disk;
import entity.TakenItem;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    IDisksDao d;

    @RequestMapping("/")
    @Transactional
    @ResponseBody
    public String hello() {
        return "hi";
    }

    @RequestMapping("/disks")
    @Transactional
    @ResponseBody
    public ModelAndView getDisks() {
        ModelAndView mv = new ModelAndView();
        List list = d.getAllDisks();
        mv.addObject("disks", list);
        return mv;
    }
    @RequestMapping("/index")
    @Transactional
    @ResponseBody
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("index");
        return mv;
    }



    @RequestMapping("/mydisks")
    @Transactional
    @ResponseBody
    public ModelAndView getUserDisks() {
        ModelAndView mv = new ModelAndView("mydisks");
        List list = d.getUserDisks();
        mv.addObject("disks", list);
        return mv;
    }


    @RequestMapping("/disks/take")
    @Transactional
    @ResponseBody
    public ModelAndView takeDisk(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("disks");
        List list = d.getAllDisks();
        mv.addObject("disks", list);
        int id = Integer.parseInt(request.getParameter("id"));
        Disk disk = new Disk();
        disk.setId(id);
        d.takeDisk(disk);
        return new ModelAndView("redirect:/disks");
        //return mv;
    }

    @RequestMapping("/mytfmd")
    @Transactional
    @ResponseBody
    public ModelAndView getTakenFromUserDisks() {
        ModelAndView mv = new ModelAndView("mytfmd");
        List list = d.getTakenFromUserDisks();
        mv.addObject("tds", list);
        return mv;
    }


    @RequestMapping("/mytd")
    @Transactional
    @ResponseBody
    public ModelAndView getTakenDisksByUser() {
        ModelAndView mv = new ModelAndView("mytd");
        List list = d.getTakenDisksByUser();
        mv.addObject("tds", list);
        return mv;
    }

    @RequestMapping("/mytd/return")
    @Transactional
    @ResponseBody
    public ModelAndView returnDisk(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ModelAndView mv = new ModelAndView("mytd");
        List list = d.getTakenDisksByUser();
        mv.addObject("tds", list);
        Disk disk = new Disk();
        disk.setId(id);
        d.returnDisk(disk);
        return new ModelAndView("redirect:/mytd");
        //return mv;
    }

}
