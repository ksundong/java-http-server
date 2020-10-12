package dev.idion.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

  private static final int DEFAULT_PORT = 80;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
      while (true) {
        try (Socket clientSocket = serverSocket.accept()) {
          BufferedReader reader = new BufferedReader(
              new InputStreamReader(clientSocket.getInputStream()));
          reader.lines().forEach(System.out::println);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
