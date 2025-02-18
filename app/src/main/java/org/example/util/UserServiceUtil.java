package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {
    public static String getHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static Boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
