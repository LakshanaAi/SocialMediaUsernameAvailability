import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * UseCase2GamingUsernameSelection
 * Demonstrates username availability checking for a gaming platform.
 */

class GamingUsernameService {

    // username -> playerId
    private HashMap<String, Integer> players = new HashMap<>();

    // username -> attempt count
    private HashMap<String, Integer> attempts = new HashMap<>();

    // Check if username is available
    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !players.containsKey(username);
    }

    // Register a new player
    public void registerPlayer(String username, int playerId) {
        players.put(username, playerId);
    }

    // Suggest gamer-style alternatives
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        suggestions.add(username + "99");
        suggestions.add(username + "_pro");
        suggestions.add("x" + username + "x");
        suggestions.add(username + "_gamer");

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

        GamingUsernameService service = new GamingUsernameService();

        // Existing players
        service.registerPlayer("DragonSlayer", 201);
        service.registerPlayer("ShadowNinja", 202);

        System.out.println("===== Gaming Platform Username Selection =====");

        String username = "DragonSlayer";

        boolean available = service.checkAvailability(username);

        System.out.println("checkAvailability(\"" + username + "\") → " + available);

        if (!available) {
            System.out.println("Suggested gamer tags: " + service.suggestAlternatives(username));
        }

        String newUser = "KnightRider";

        boolean newAvailable = service.checkAvailability(newUser);

        System.out.println("checkAvailability(\"" + newUser + "\") → " + newAvailable);

        if (newAvailable) {
            service.registerPlayer(newUser, 203);
            System.out.println(newUser + " successfully registered.");
        }

        System.out.println("Most attempted username → " + service.getMostAttempted());
    }
}
