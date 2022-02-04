package emailservice;

import java.util.HashSet;
import java.util.Set;

public class EmailService {
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return new HashSet<>(users);
    }

    public void registerUser(String emailAddress){
        boolean alreadyUsed = users.stream()
                .filter(u -> u.getEmailAddress().equals(emailAddress))
                .toList().size() > 0;
        if (alreadyUsed){
            throw new IllegalArgumentException("Email address is already taken!");
        }

        if(isCorrect(emailAddress) ){
            User newUser  = new User(emailAddress);
            if(!users.contains(newUser)){
                users.add(newUser);
            }
        }else{
            throw new IllegalArgumentException("Email address is not valid: JohnDoe@gmail.com");
        }
    }

    private boolean isCorrect(String emailAddress){
        if (emailAddress == null){
            return false;
        }
        int indexOfAt = emailAddress.indexOf("@");
        int indexOfDot = emailAddress.indexOf(".");
        return indexOfAt>0 && indexOfDot>(indexOfAt+1);
    }

    public void sendEmail(String from, String to, String subject, String content){

    }

    public void sendSpam(String subject, String content){
        users.forEach(user -> {
            Spam spam = new Spam(user,content, subject);
            user.getEmail(spam);
        });
    }
}
