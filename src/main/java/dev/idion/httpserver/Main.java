package dev.idion.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger log = LoggerFactory.getLogger(Main.class);
  private static final int DEFAULT_PORT = 80;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
      while (true) {
        try (Socket client = serverSocket.accept()) {
          handleClient(client);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void handleClient(Socket client) throws IOException {
    log.debug("New Client: {}", client);
    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

    StringBuilder requestBuilder = new StringBuilder();
    String line;

    while (!(line = br.readLine()).isBlank()) {
      requestBuilder.append(line).append("\r\n");
    }

    log.debug(requestBuilder.toString());
  }
}
