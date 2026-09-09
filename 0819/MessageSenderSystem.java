interface MessageSender {
    boolean send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || receiver.isBlank() || message == null || message.isBlank()) return false;
        System.out.println("EMAIL to " + receiver + ": " + message);
        return true;
    }
}

class SmsSender implements MessageSender {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || receiver.isBlank() || message == null || message.isBlank()) return false;
        System.out.println("SMS to " + receiver + ": " + message);
        return true;
    }
}

class ConsoleSender implements MessageSender {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || receiver.isBlank() || message == null || message.isBlank()) return false;
        System.out.println("CONSOLE to " + receiver + ": " + message);
        return true;
    }
}

public class MessageSenderSystem {
    static void notify(MessageSender sender, String receiver, String message) {
        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "amy@example.com", "Hello");
        notify(sms, "0912345678", "Class update");
        notify(console, "Admin", "System online");
        notify(email, " ", "Empty receiver test");
    }
}