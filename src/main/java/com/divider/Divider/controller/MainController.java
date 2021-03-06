package com.divider.Divider.controller;

import com.divider.Divider.dto.PredictHostRequest;
import com.divider.Divider.service.Algo;
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
      @RequestParam(name = "checkHost", required = false) String checkHost,
      @RequestParam(name = "algo", required = false) Long algoId
  ) throws IOException {
    if (checkHost != null && algoId != null) {
      model.addAttribute("hostType", mainService.predictHost(checkHost, algoId));
    }
    model.addAttribute("predictHostRequest", new PredictHostRequest(checkHost, algoId));
    model.addAttribute("algos", Algo.getAlgoList());
    return "host";
  }

  @PostMapping("/host")
  public String inputFormSubmit(
      Model model,
      @Valid PredictHostRequest predictHostRequest,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("algos", Algo.getAlgoList());
      return "host";
    }

    return "redirect:/?checkHost="
        + predictHostRequest.getName()
        + "&algo="
        + predictHostRequest.getAlgoId();
  }
}
