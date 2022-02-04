package emailservice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class User {
    private String emailAddress;
    private List<Email> incoming = new ArrayList<>();
    private List<Email> sent = new ArrayList<>();
    boolean hasSpamFilter;

    public boolean hasSpamFilter() {
        return hasSpamFilter;
    }

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void getEmail(Email email){
        if(hasSpamFilter
                && email.getFrom().getEmailAddress().contains("spam")){
            throw new IllegalStateException("Be careful, tis is a spam!");
        }
        incoming.add(email);
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public void spamFilterChange(){
        hasSpamFilter = !hasSpamFilter;
    }

    public void sendEmail(String subject, String content, User to){
        Normal normal = new Normal(this, to, subject, content );
        sent.add(normal);
        to.getEmail(normal);
    }

    public List<Email> getIncoming(){
        List<Email> sorted = new ArrayList<>(incoming);
        Comparator<? super Email> comparator = new Comparator<Email>() {
            @Override
            public int compare(Email e1, Email e2) {
                int important1=e1.isImportant() ? 1 : 0;
                int important2=e2.isImportant() ? 1 : 0;
                return important1 - important2;
            }
        };
        sorted.sort(comparator);
        return sorted;
    }
    public List<Email> getSent(){
        return new ArrayList<>(sent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return hasSpamFilter == user.hasSpamFilter
                && emailAddress.equals(user.emailAddress)
                && Objects.equals(incoming, user.incoming)
                && Objects.equals(sent, user.sent);
    }

    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }
}
