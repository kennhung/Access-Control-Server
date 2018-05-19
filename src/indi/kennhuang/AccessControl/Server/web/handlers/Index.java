package indi.kennhuang.AccessControl.Server.web.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class Index implements HttpHandler {

    String string_404 = "<h1>404 Not Found</h1>" +
            "No context found for request";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        OutputStream os = httpExchange.getResponseBody();
        if(!"/".equals(httpExchange.getRequestURI().getPath())){
            httpExchange.sendResponseHeaders(404, string_404.length());
            os.write(string_404.getBytes());
            os.close();
        }
        else{
            String response = "This is the response";
            httpExchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}
