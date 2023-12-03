package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final String TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    private static final int HTTP_OK = 200;

    private final HttpClient httpClient;

    public HackerNews() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public long[] hackerNewsTopStories() {
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(TOP_STORIES)).build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HTTP_OK) {
                String[] ids = response.body().replaceAll("[\\[|\\]]", "").split(",");

                long[] result = new long[ids.length];
                for (int i = 0; i < ids.length; i++) {
                    result[i] = Long.parseLong(ids[i].trim());
                }

                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new long[0];
    }

    public String news(long id) {
        try {
            String url = String.format(ITEM, id);
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HTTP_OK) {

                Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
                Matcher matcher = pattern.matcher(response.body());

                if (matcher.find()) {
                    return matcher.group(1);
                } else {
                    throw new RuntimeException("Названия нет");
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "";
    }

}
