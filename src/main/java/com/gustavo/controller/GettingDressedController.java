package com.gustavo.controller;

import com.gustavo.services.GettingDressedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/gettingdressed")
public class GettingDressedController {

    private final GettingDressedService service;

    @Autowired
    GettingDressedController(GettingDressedService service){
        this.service = service;
    }

    @RequestMapping(value = "{temperature}/{commands}", method = RequestMethod.GET)
    String dress(@PathVariable("temperature") String temperature, @PathVariable("commands") String commands){
        return service.gettingDressed(temperature, commands);
    }

    @RequestMapping(method = RequestMethod.POST)
    String getDress(@RequestBody Map<String, String> description){
        return service.gettingDressed(description.get("temperature"), description.get("commands"));
    }
}
