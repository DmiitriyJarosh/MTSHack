package com.divider.Divider.utils;

import java.io.*;
import java.lang.reflect.Executable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HostValidator {
    public static boolean executePost(String targetURL) throws IOException {
        URL url = new URL("https://" + targetURL + "/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(3000);
        con.setReadTimeout(3000);
        con.setInstanceFollowRedirects(false);
        String target = con.getHeaderField("Location");
        //System.out.println(targetURL);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        if (target != null) {
            return target.equals("https://" + targetURL + "/") && content.toString().contains("html>");
        } else
            return content.toString().contains("html>");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("/home/vladimir/Desktop/output_undefined.csv");
        //File file = new File("/home/vladimir/Git/untitled/src/main/java/host_without_duplicates.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));
        ExecutorService service = Executors.newFixedThreadPool(9);
        String st;
        ConcurrentHashMap<String, Boolean> ans = new ConcurrentHashMap<>();
        AtomicInteger counter = new AtomicInteger(0);
        while ((st = br.readLine()) != null) {
            String finalSt = st;
            service.submit(() -> {
                try {
                    if (counter.incrementAndGet() % 1000 == 0) {
                        System.out.println(counter.get() + " " + finalSt);
                    }
                    ans.put(finalSt, executePost(finalSt));
                } catch (Exception e) {
                    if (counter.incrementAndGet() % 1000 == 0) {
                        System.out.println(counter.get() + " " + finalSt);
                    }
                    ans.put(finalSt, false);
                }
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        File output = new File("/home/vladimir/Git/untitled/src/main/java/out.csv");
        BufferedWriter outputStream = new BufferedWriter(new FileWriter(output));
        for (Map.Entry<String, Boolean> entry : ans.entrySet()) {
            outputStream.write(entry.getKey() + "," +  (entry.getValue() ? "1": "0") + "\n");
        }
        outputStream.close();
    }
}
