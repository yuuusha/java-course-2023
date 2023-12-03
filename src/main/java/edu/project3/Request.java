package edu.project3;

public class Request {
    private final String requestType;
    private final String source;
    private final String httpProtocol;

    public Request(String requestMethod, String resource, String protocol) {
        this.requestType = requestMethod;
        this.source = resource;
        this.httpProtocol = protocol;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getSource() {
        return source;
    }

    public String getHttpProtocol() {
        return httpProtocol;
    }
}
