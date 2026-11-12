package rs.ac.uns.ftn.radnickirecepti.data.repositories;

public class UserRepository {
    private static UserRepository repo;

    public static UserRepository get() {
        if (repo != null) {
            return repo;
        }
        repo = new UserRepository();
        return repo;
    }

    public boolean UserExists(String s) {
        //TODO: Fix
        return false;
    }
}
