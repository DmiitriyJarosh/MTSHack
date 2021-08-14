package com.divider.Divider.controller;

import com.divider.Divider.dto.Host;
import com.divider.Divider.service.MainService;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  public final MainService mainService;

  @Autowired
  public MainController(MainService mainService) {
    this.mainService = mainService;
  }

  @GetMapping
  public String inputForm(
      Model model,
      @RequestParam(name = "checkHost", required = false) String checkHost
  ) throws IOException {
    if (checkHost != null) {
      model.addAttribute("hostType", mainService.predictHost(checkHost));
      model.addAttribute("host", new Host(checkHost));
    } else {
      model.addAttribute("host", new Host());
    }
    return "host";
  }

  @PostMapping("/host")
  public String inputFormSubmit(
      @Valid Host host,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return "host";
    }

    return "redirect:/?checkHost=" + host.getName();
  }
}
