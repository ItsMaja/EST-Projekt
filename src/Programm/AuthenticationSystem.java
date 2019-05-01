package Programm;

/**
 * @author Maja Wandura
 */
public class AuthenticationSystem {

    public enum Role {
        MITARBEITER, GESCHAEFTSFUEHRUNG
    }

    private static Role userRole;

    public static boolean validateCredentials(Role role, String password) {
        if (role == Role.MITARBEITER && password.equals("platin") ||
                (role == Role.GESCHAEFTSFUEHRUNG && password.equals("diamant"))) {
            userRole = role;
            return true;
        } else {
            return false;
        }
    }

    public static Role getUserRole() {
        return userRole;
    }
}
