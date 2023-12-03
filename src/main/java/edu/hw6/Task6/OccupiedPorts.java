package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.util.Map.entry;

public final class OccupiedPorts {

    private static final int MAX_PORTS = 49151;

    private final static Logger LOGGER = LogManager.getLogger();

    private static final Map<Integer, String> MAP_PORTS = Map.ofEntries(
        entry(123, "NTP"),
        entry(135, "EPMAP"),
        entry(137, "NETBIOS-NS"),
        entry(138, "NETBIOS-DGM"),
        entry(139, "NETBIOS-SSN"),
        entry(445, "MICROSOFT-DS"),
        entry(500, "ISAKMP"),
        entry(1900, "Microsoft SSDP"),
        entry(3702, "WS-Discovery"),
        entry(4500, "IPsec NAT traversal"),
        entry(5353, "Multicast DNS"),
        entry(5355, "LLMNR"),
        entry(9080, "DrWeb Enterprise Suite Web Control")
    );

    private OccupiedPorts() {

    }

    private enum Status {
        FREE("Свободно"),
        OCCUPIED("Занято"),
        UNKNOWN("Неизвестен");

        final String message;
        Status(String s) {
            message = s;
        }

        public String getMessage() {
            return message;
        }
    }

    private enum Type {
        TCP("TCP"),
        UPD("UPD");

        final String message;
        Type(String s) {
            message = s;
        }

        public String getMessage() {
            return message;
        }
    }

    public static void scanPorts() {
        for (int port = 0; port <= MAX_PORTS; port++) {
            LOGGER.info(scanPortTCP(port));
            LOGGER.info(scanPortUPD(port));
        }
    }

    public static String scanPortTCP(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
            return Type.TCP.getMessage() + '\t' + port + '\t' + Status.FREE.getMessage();
        } catch (IOException e) {
            return Type.TCP.getMessage() + '\t' + port + '\t' + Status.OCCUPIED.getMessage() + '\t'
                + MAP_PORTS.getOrDefault(port, Status.UNKNOWN.getMessage());
        }
    }

    public static String scanPortUPD(int port) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.close();
            return Type.UPD.getMessage() + '\t' + port + '\t' + Status.FREE.getMessage();
        } catch (IOException e) {
            return Type.UPD.getMessage() + '\t' + port + '\t' + Status.OCCUPIED.getMessage() + '\t'
                + MAP_PORTS.getOrDefault(port, Status.UNKNOWN.getMessage());
        }
    }
}
