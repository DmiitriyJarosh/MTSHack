package com.divider.Divider.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MainService {

  @Value("${ml.script}")
  private String mlFilename;

  public HostType predictHost(String host) throws IOException {
    Runtime rt = Runtime.getRuntime();
    String[] commands = {mlFilename};
    Process proc = rt.exec(commands);

    BufferedReader stdInput = new BufferedReader(new
        InputStreamReader(proc.getInputStream()));

    return HostType.buildFromCode(Integer.parseInt(stdInput.readLine()));
  }
}
