package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    private static final Logger logger = LogManager.getLogger(MainApp.class);

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("model1", 1)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("model2", 2)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("model3", 3)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("model4", 4)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            logger.info(user);
        }
        logger.info("--------------------------- USERS BY MODEL AND SERIES CAR --------------------------------------------");
        List<User> usersByCar = userService.listUsersByCar("model1", 1);
        for (User user : usersByCar) {
            logger.info(user);
        }
        context.close();
    }
}
