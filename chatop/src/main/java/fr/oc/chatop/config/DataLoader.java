package fr.oc.chatop.config;
import fr.oc.chatop.entity.Rental;
import fr.oc.chatop.entity.User;
import fr.oc.chatop.repos.RentalRepo;
import fr.oc.chatop.repos.UserRepos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepos userRepos;
    private final RentalRepo rentalRepos;

    public DataLoader(UserRepos userRepos, RentalRepo rentalRepos) {
        this.userRepos = userRepos;
        this.rentalRepos = rentalRepos;
    }

    @Override
    public void run(String... args) {

        if (userRepos.count() == 0 && rentalRepos.count() == 0) {
            System.out.println("Data initializing for testing.");
            User user = new User();

            user.setName("Test TEST");
            user.setEmail("test@test.com");
            user.setCreated_at("2022/02/02");
            user.setUpdated_at("2022/08/02");

            userRepos.save(user);


            Rental rental = new Rental();

            rental.setName("test house 1");
            rental.setSurface(432L);
            rental.setPrice(300.0);
            rental.setPicture("https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg");
            rental.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum. Praesent mollis neque sed sagittis ultricies. Suspendisse congue ligula at justo molestie, eget cursus nulla tincidunt. Pellentesque elementum rhoncus arcu, viverra gravida turpis mattis in. Maecenas tempor elementum lorem vel ultricies. Nam tempus laoreet eros, et viverra libero tincidunt a. Nunc vel nisi vulputate, sodales massa eu, varius erat.");
            rental.setOwner(user);
            rental.setUpdated_at("2014/12/02");

            rentalRepos.save(rental);

            System.out.println("Data initialized for testing.");
        }
    }
}