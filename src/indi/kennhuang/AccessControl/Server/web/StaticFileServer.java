package indi.kennhuang.AccessControl.Server.web;

import java.io.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class StaticFileServer implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String fileId = exchange.getRequestURI().getPath();
        InputStream is = getInputStream(fileId);
        if (is == null) {
            String response = "Error 404 File not found.";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream output = exchange.getResponseBody();
            output.write(response.getBytes());
            output.flush();
            output.close();
        } else {
            exchange.sendResponseHeaders(200, 0);
            OutputStream output = exchange.getResponseBody();
            String line;
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while ((line=br.readLine()) != null) {
                output.write(line.getBytes());
            }

            output.flush();
            output.close();
            br.close();
            isr.close();
            is.close();
        }
    }

    private InputStream getInputStream(String fileId) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+fileId.substring(5, fileId.length()));
        if (fileId.equals("/res") || fileId.equals("/res/")) {
            return null;
        }
        return is;
    }

}