package emailservice;

public class Spam implements Email{
    private User to;
    private String content;
    private String subject;

    public Spam(User to, String content, String subject) {
        this.to = to;
//        this.content = content;  //hibás teszt
//        this.subject = subject;
         this.content = subject;
         this.subject = content;
    }

    @Override
    public User getFrom() {
        return new User("spam@spam.com");
    }

    @Override
    public User getTo() {
        return to;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public boolean isImportant() {
        return false;
    }
}
