import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * UseCase3EmailAvailabilityChecker
 * Demonstrates email availability checking using HashMap.
 */

class EmailService {

    // email -> userId
    private HashMap<String, Integer> emails = new HashMap<>();

    // email -> attempt count
    private HashMap<String, Integer> attempts = new HashMap<>();

    // Check email availability
    public boolean checkAvailability(String email) {
        attempts.put(email, attempts.getOrDefault(email, 0) + 1);
        return !emails.containsKey(email);
    }

    // Register email
    public void registerEmail(String email, int userId) {
        emails.put(email, userId);
    }

    // Suggest alternative email addresses
    public List<String> suggestAlternatives(String email) {

        List<String> suggestions = new ArrayList<>();

        String name = email.split("@")[0];
        String domain = email.split("@")[1];

        suggestions.add(name + "1@" + domain);
        suggestions.add(name + "123@" + domain);
        suggestions.add(name + ".official@" + domain);
        suggestions.add(name + "_mail@" + domain);

        return suggestions;
    }

    // Get most attempted email
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

        EmailService service = new EmailService();

        // Existing emails
        service.registerEmail("john@gmail.com", 301);
        service.registerEmail("admin@gmail.com", 302);

        System.out.println("===== Email Address Availability Checker =====");

        String email = "john@gmail.com";

        boolean available = service.checkAvailability(email);

        System.out.println("checkAvailability(\"" + email + "\") → " + available);

        if (!available) {
            System.out.println("Suggested alternatives: " + service.suggestAlternatives(email));
        }

        String newEmail = "jane@gmail.com";

        boolean newAvailable = service.checkAvailability(newEmail);

        System.out.println("checkAvailability(\"" + newEmail + "\") → " + newAvailable);

        if (newAvailable) {
            service.registerEmail(newEmail, 303);
            System.out.println(newEmail + " successfully registered.");
        }

        System.out.println("Most attempted email → " + service.getMostAttempted());
    }
}
