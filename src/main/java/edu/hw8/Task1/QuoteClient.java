package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class QuoteClient {

    private final SocketChannel clientSocketChannel;

    public QuoteClient(String hostname, int port) throws IOException {
        clientSocketChannel = SocketChannel.open();
        clientSocketChannel.connect(new InetSocketAddress(hostname, port));
    }

    @SuppressWarnings("MagicNumber")
    public String getQuote(String keyword) throws IOException {
        ByteBuffer writeByteBuffer = ByteBuffer.wrap(keyword.getBytes());
        try {
            clientSocketChannel.write(writeByteBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<String> response = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            ByteBuffer readByteBuffer = ByteBuffer.allocate(1024);
            try {
                int bytesRead = clientSocketChannel.read(readByteBuffer);
                String answer = new String(readByteBuffer.array(), 0, bytesRead, StandardCharsets.UTF_8);
                response.complete(answer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return response.join();
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String keyword = scanner.nextLine();
//        String quote = "";
//
//        try {
//            QuoteClient client = new QuoteClient("localhost", 5555);
//            quote = client.getQuote(keyword);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(quote);
//    }
}
