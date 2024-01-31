package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class LogRecord implements Comparable<LogRecord> {
    private String remoteAddress;
    private String remoteUsername;
    private OffsetDateTime time;
    private Request request;
    private int statusCode;
    private int bytesSent;
    private String httpReferer;
    private String httpUserAgent;

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public String getRemoteUsername() {
        return remoteUsername;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public Request getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesSent() {
        return bytesSent;
    }

    public String getHttpReferer() {
        return httpReferer;
    }

    public String getHttpUserAgent() {
        return httpUserAgent;
    }

    @SuppressWarnings("MagicNumber")
    public LogRecord(String inputRecord) {
        String regex =
            "^((\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})|(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4"
                + "}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}"
                + "|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|("
                + "[0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9"
                + "a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25"
                + "[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{"
                + "1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9"
                + "]))) -(( )| (\\S+) )- \\[(.+)\\] \"([A-Z]+) ([^\\s]+) (HTTP\\/[0-9.]+)\" (\\d+) (\\d+) \"([^\"]*)\" "
                + "\"([^\"]*)\"$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputRecord);

        if (matcher.find()) {
            if (matcher.group(2) != null) {
                this.remoteAddress = matcher.group(2);
            } else {
                this.remoteAddress = matcher.group(3);
            }
            if (matcher.group(34) != null) {
                this.remoteUsername = matcher.group(34);
            } else {
                this.remoteUsername = matcher.group(35);
            }
            this.time =
                OffsetDateTime.parse(
                    matcher.group(36),
                    DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)
                );
            this.request = new Request(matcher.group(37), matcher.group(38), matcher.group(39));
            this.statusCode = Integer.parseInt(matcher.group(40));
            this.bytesSent = Integer.parseInt(matcher.group(41));
            this.httpReferer = matcher.group(42);
            this.httpUserAgent = matcher.group(43);
        }
    }

    @Override
    public int compareTo(@NotNull LogRecord o) {
        return this.time.compareTo(o.time);
    }
}
