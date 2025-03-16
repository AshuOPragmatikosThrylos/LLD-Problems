public class Notification {
    private String message;
    private User recipient;

    public Notification(String message, User recipient) {
        this.message = message;
        this.recipient = recipient;
    }

    public void send() {
        System.out.println("Notification sent to " + recipient.getName() + ": " + message);
    }
}
