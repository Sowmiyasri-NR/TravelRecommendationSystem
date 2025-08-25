import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravelRecommendationApp {

    public static void main(String[] args) {
        TravelRecommendationSystem recommendationSystem = new TravelRecommendationSystem();
        addSampleDestinations(recommendationSystem);

        Scanner sc = new Scanner(System.in);

        System.out.println("🌍 Welcome to the Travel Recommendation System!");

        System.out.print("Enter preferred location (Asia, Europe, North America, South America, Africa, Australia): ");
        String location = sc.nextLine();

        System.out.print("Enter preferred activity (Beach, Sightseeing, Shopping, Cultural, Adventure): ");
        String activity = sc.nextLine();

        System.out.print("Enter your max budget(1500,2000,1800,1700): ");
        double budget = 0;
        try {
            budget = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid budget entered! Exiting...");
            System.exit(0);
        }

        List<Destination> recommendations = recommendationSystem.recommend(location, activity, budget);

        if (recommendations.isEmpty()) {
            System.out.println("\n No destinations found within your criteria.");
        } else {
            System.out.println("\n Recommended Destinations:");
            for (Destination d : recommendations) {
                System.out.println(d);
            }
        }

        sc.close();
    }

    private static void addSampleDestinations(TravelRecommendationSystem system) {
        system.addDestination(new Destination("Paris", "Europe", "Sightseeing", 1500));
        system.addDestination(new Destination("Maldives", "Asia", "Beach", 2000));
        system.addDestination(new Destination("New York", "North America", "Shopping", 1800));
        system.addDestination(new Destination("Tokyo", "Asia", "Cultural", 1700));
    }

      static class Destination {
        String name;
        String location;
        String activity;
        double budget;

        public Destination(String name, String location, String activity, double budget) {
            this.name = name;
            this.location = location;
            this.activity = activity;
            this.budget = budget;
        }

        @Override
        public String toString() {
            return "Destination{" +
                    "name='" + name + '\'' +
                    ", location='" + location + '\'' +
                    ", activity='" + activity + '\'' +
                    ", budget=" + budget +
                    '}';
        }
    }

    static class TravelRecommendationSystem {
        private List<Destination> destinations = new ArrayList<>();

        public void addDestination(Destination destination) {
            destinations.add(destination);
        }

        public List<Destination> recommend(String preferredLocation, String preferredActivity, double maxBudget) {
            List<Destination> recommended = new ArrayList<>();
            for (Destination d : destinations) {
                if ((preferredLocation == null || d.location.equalsIgnoreCase(preferredLocation)) &&
                    (preferredActivity == null || d.activity.equalsIgnoreCase(preferredActivity)) &&
                    d.budget <= maxBudget) {
                    recommended.add(d);
                }
            }
            return recommended;
        }
    }
} 