package ru.job4j.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.UsageLog4j;

public class EchoServer {
    private static final Logger LOG_ECHO_SERVER = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); Objects.nonNull(str) && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("Exit")) {
                            out.write("Server connection is close".getBytes());
                            server.close();
                        } else if (str.contains("Hello")) {
                            out.write("Hello my friend".getBytes());
                        } else if (str.contains("What")) {
                            out.write("What you want".getBytes());
                        }
                    }
                    out.flush();
                } catch (IOException exception) {
                    LOG_ECHO_SERVER.error("Exception in log example", exception);
                }
            }
        } catch (IOException exception) {
            LOG_ECHO_SERVER.error("Exception in log example", exception);
        }
    }
}