package emailservice;

public interface Email {
    User getFrom();
    User getTo();
    String getSubject();
    String getContent();
    boolean isImportant();
}
