package indi.kennhuang.AccessControl.Server.web;

import com.sun.net.httpserver.HttpServer;
import indi.kennhuang.AccessControl.Server.web.handlers.Index;

import java.io.*;
import java.net.*;

public class WebServer implements Runnable {

    private static HttpServer httpServer = null;
    private static boolean shutdown = false;

    public void startHttpServer() throws IOException {
        System.out.println("Web Server Starting on Port 8000");
        httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        httpServer.createContext("/res", new StaticFileServer());
        httpServer.createContext("/", new Index());
        httpServer.setExecutor(null);
        httpServer.start();

    }

    @Override
    public void run() {
        try {
            this.startHttpServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    /*
        try {
            DatagramSocket serverSocket = new DatagramSocket();
            // 发送的目的地址和端口
            InetAddress destination = InetAddress.getByName("255.255.255.255");
            while (!shutdown) {
                byte[] buf = "Hello client".getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, destination, 9999);
                serverSocket.send(packet);
                TimeUnit.SECONDS.sleep(5);
            }
            serverSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

    }

}