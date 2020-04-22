//package mypackage;
//
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//public class UserStore {
//    public static Map<String, User> dbUsers;
//
//    static {
//        dbUsers.put("student", new User("student", "123456", 15));
//        dbUsers.put("teacher", new User("teacher", "123456", 40));
//    }
//
//    public static User findUserByName(String username) {
//        return dbUsers.get(username);
//    }
//}
