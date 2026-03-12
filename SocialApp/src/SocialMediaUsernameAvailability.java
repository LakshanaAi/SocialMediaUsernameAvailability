import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * UseCase1UsernameRegistration
 * Demonstrates username availability checking for a social media platform
 * like Twitter or Instagram using HashMap for O(1) lookup.
 */

class UsernameService {

    // username -> userId
    private HashMap<String, Integer> users = new HashMap<>();

    // username -> attempt count
    private HashMap<String, Integer> attempts = new HashMap<>();

    // Check availability
    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    // Register username
    public void registerUser(String username, int userId) {
        users.put(username, userId);
    }

    // Suggest alternative usernames
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        suggestions.add(username + "1");
        suggestions.add(username + "123");
        suggestions.add(username.replace("_", "."));
        suggestions.add(username + "_official");

        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {

        String most = "";
        int max = 0;

        for (String key : attempts.keySet()) {

            int count = attempts.get(key);

            if (count > max) {
                max = count;
                most = key;
            }
        }

        return most + " (" + max + " attempts)";
    }
}

public class SocialMediaUsernameAvailability {

    public static void main(String[] args) {

        UsernameService service = new UsernameService();

        // Existing users
        service.registerUser("john_doe", 101);
        service.registerUser("admin", 1);

        System.out.println("===== Social Media Username Registration =====");

        String username = "john_doe";

        boolean available = service.checkAvailability(username);

        System.out.println("checkAvailability(\"" + username + "\") → " + available);

        if (!available) {
            System.out.println("Suggested alternatives: " + service.suggestAlternatives(username));
        }

        String newUser = "jane_smith";

        boolean newAvailable = service.checkAvailability(newUser);

        System.out.println("checkAvailability(\"" + newUser + "\") → " + newAvailable);

        if (newAvailable) {
            service.registerUser(newUser, 102);
            System.out.println(newUser + " successfully registered.");
        }

        System.out.println("Most attempted username → " + service.getMostAttempted());
    }
}
