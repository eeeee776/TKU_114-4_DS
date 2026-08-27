package midterm_exam;

public class Q04_NotificationRouter {
    public interface Channel {
        String name();
        boolean supports(String destination);
        String send(String destination, String message);
    }

    public static class EmailChannel implements Channel {
        @Override
        public String name() { return "EMAIL"; }

        @Override
        public boolean supports(String destination) {
            if (destination == null) return false;
            int idx = destination.indexOf('@');
            return idx > 0 && idx < destination.length() - 1;
        }

        @Override
        public String send(String destination, String message) {
            return name() + "|" + destination + "|" + message;
        }
    }

    public static class SmsChannel implements Channel {
        @Override
        public String name() { return "SMS"; }

        @Override
        public boolean supports(String destination) {
            if (destination == null) return false;
            String digits = destination.replace("-", "");
            if (digits.length() != 10) return false;
            for (char c : digits.toCharArray()) {
                if (!Character.isDigit(c)) return false;
            }
            return true;
        }

        @Override
        public String send(String destination, String message) {
            return name() + "|" + destination + "|" + message;
        }
    }

    private static void routeCheckpointM26() {
        // checkpoint helper
    }

    public static java.util.List<String> route(
            java.util.List<Channel> channels,
            String destination,
            String message) {
        routeCheckpointM26();
        java.util.List<String> results = new java.util.ArrayList<>();
        if (channels == null || destination == null || message == null) return results;
        
        for (Channel ch : channels) {
            if (ch != null && ch.supports(destination)) {
                results.add(ch.send(destination, message));
            }
        }
        return results;
    }
}
