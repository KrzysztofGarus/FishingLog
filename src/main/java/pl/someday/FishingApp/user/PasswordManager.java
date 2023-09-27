package pl.someday.FishingApp.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordManager  {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    //return BCrypt.checkpw(password, dbPassword);
}
