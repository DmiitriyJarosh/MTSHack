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

  @Value("${ml.model.prefix}")
  private String modelFilenamePrefix;

  @Value("${python.path}")
  private String pythonPath;

  public HostType predictHost(String host, Long algoId) throws IOException {
    Runtime rt = Runtime.getRuntime();
    String[] commands = {pythonPath, mlFilename, host, modelFilenamePrefix + algoId + ".pickle"};
    Process proc = rt.exec(commands);

    BufferedReader stdInput = new BufferedReader(
        new InputStreamReader(proc.getInputStream())
    );
    BufferedReader stdErr = new BufferedReader(
        new InputStreamReader(proc.getErrorStream())
    );

    String result = stdInput.readLine();
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    System.out.println(stdErr.readLine());
    return HostType.buildFromCode(Integer.parseInt(result));
  }
}
