package com.company;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class BestDNSServerFinder {

    public static void main(String[] args) {
        String[] dnsServers = {"8.8.8.8", "1.1.1.1", "9.9.9.9"}; // Example DNS servers to test
        Map<String, Long> serverResponseTimes = new HashMap<>();

        for (String dnsServer : dnsServers) {
            long startTime = System.currentTimeMillis();
            try {
                InetAddress address = InetAddress.getByName("example.com"); // Replace with your target domain
                address = InetAddress.getByName(address.getHostAddress());
                // Use the DNS server explicitly by providing the InetAddress of the DNS server
                InetAddress dns = InetAddress.getByName(dnsServer);
                address = InetAddress.getByAddress(address.getHostName(), address.getAddress());

                long endTime = System.currentTimeMillis();
                long responseTime = endTime - startTime;
                serverResponseTimes.put(dnsServer, responseTime);
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }

        // Find the DNS server with the lowest response time
        String bestDNSServer = findBestDNSServer(serverResponseTimes);
        System.out.println("Best DNS Server: " + bestDNSServer);
    }

    // Function to find the DNS server with the lowest response time
    private static String findBestDNSServer(Map<String, Long> serverResponseTimes) {
        long minTime = Long.MAX_VALUE;
        String bestServer = null;

        for (Map.Entry<String, Long> entry : serverResponseTimes.entrySet()) {
            if (entry.getValue() < minTime) {
                minTime = entry.getValue();
                bestServer = entry.getKey();
            }
        }

        return bestServer;
    }
}
