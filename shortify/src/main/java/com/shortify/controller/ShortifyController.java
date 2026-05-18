package com.shortify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shortify.model.Url;
import com.shortify.service.ShortifyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ShortifyController {
    @Autowired
    private ShortifyService shortifyService;

    @RequestMapping(path="/urlShortener", method=RequestMethod.GET)
    public String showForm(Model model){
        if(!model.containsAttribute("url")) {
            model.addAttribute("url", new Url());
        }
        return "index";
    }


    @RequestMapping(path="/urlShortener", method=RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute("url") Url url, BindingResult result, Model model, HttpServletRequest request){
        if(result.hasErrors()){
            // System.out.println(result.getAllErrors()); //added for debugging purpose
            return "index";
        }

        shortifyService.generateShortUrlCode(url);
        // String shortUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())+"/"+url.getShortCode();
        //better approach below
        String shortUrl = request.getScheme() + "://" +
                  request.getServerName() + ":" +
                  request.getServerPort() +
                  request.getContextPath() + "/" +
                  url.getShortCode();

        model.addAttribute("originalUrl", url.getOriginalUrl());
        model.addAttribute("shortUrl", shortUrl);        
        return "index";
    }

    @RequestMapping(path = "/{shortUrlCode}", method=RequestMethod.GET)
    public String accessShortUrl(@PathVariable("shortUrlCode") String shortCode, Model model){
        //look up in the database for given shortCode and check for non-expiry
        Url url = shortifyService.getUrlByShortCode(shortCode);        
        
        //show display message "url expired or no longer exists!"
        if(url == null){
            model.addAttribute("errorMsg", "Url expired and no longer exists!");
            return "pageNotFound";
        }

        //if url found redirect to the original Url. 
        String originalUrl = url.getOriginalUrl();        
        // Also update(increment) clickCount --> this logic I'll add into service layer directly to decrease latency of the  application
        
        return "redirect:"+originalUrl;
    }
}
