interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public void send(String receiver, String message) { System.out.println("Email -> " + receiver + ": " + message); }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String receiver, String message) { System.out.println("SMS -> " + receiver + ": " + message); }
}

class ConsoleSender implements MessageSender {
    @Override
    public void send(String receiver, String message) { System.out.println("Console -> " + receiver + ": " + message); }
}

public class MessageSenderSystem {
    static void notify(MessageSender sender, String receiver, String message) {
        if (receiver == null || receiver.isBlank() || message == null || message.isBlank()) {
            System.out.println("錯誤：收件人或訊息不可為空");
            return;
        }
        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        notify(new EmailSender(), "amy@example.com", "Hello");
        notify(new SmsSender(), "0912345678", "Your code is 1234");
        notify(new ConsoleSender(), "", "Test"); // 測試邊界條件
    }
}