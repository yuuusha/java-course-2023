package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class QuoteServer {

    private QuoteServer() {

    }

    @SuppressWarnings("MagicNumber")
    public static void startServer() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(5555));

            while (true) {
                SocketChannel clientSocketChannel = serverSocketChannel.accept();
                executorService.submit(new ClientHandler(clientSocketChannel));
            }
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class ClientHandler implements Runnable {
        private final SocketChannel clientSocketChannel;

        ClientHandler(SocketChannel clientSocketChannel) {
            this.clientSocketChannel = clientSocketChannel;
        }

        @SuppressWarnings("MagicNumber")
        @Override
        public void run() {
            try {
                ByteBuffer readByteBuffer = ByteBuffer.allocate(128);
                int bytesRead = clientSocketChannel.read(readByteBuffer);

                String keyword = new String(readByteBuffer.array(), 0, bytesRead, StandardCharsets.UTF_8).trim();

                String response = QuoteBank.get(keyword);
                ByteBuffer writeByteBuffer = ByteBuffer.wrap(response.getBytes());

                clientSocketChannel.write(writeByteBuffer);
                clientSocketChannel.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public static void main(String[] args) {
//        startServer();
//    }
}

