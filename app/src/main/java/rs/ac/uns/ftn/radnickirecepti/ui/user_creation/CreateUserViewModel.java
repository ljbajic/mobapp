package rs.ac.uns.ftn.radnickirecepti.ui.user_creation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import rs.ac.uns.ftn.radnickirecepti.R;
import rs.ac.uns.ftn.radnickirecepti.data.repositories.UserRepository;

public class CreateUserViewModel extends ViewModel {
    private final MutableLiveData<String> username = new MutableLiveData<>("");
    private final MutableLiveData<String> email = new MutableLiveData<>("");
    private final MutableLiveData<String> password = new MutableLiveData<>("");
    private final MutableLiveData<String> repeatPassword = new MutableLiveData<>("");

    public int validateUsername() {
        String Username = username.getValue();
        if (Username == null) return -1;
        if (Username.length() < 3) {
            return R.string.username_too_short;
        }
        if (!Username.matches("[A-Za-zd_]*")) {
            return R.string.username_invalid_chars;
        }
        if (UserRepository.get().UserExists(username.getValue())) {
            return R.string.username_taken;
        }
        return -1;
    }

    public int validateEmail() {
        String Email = email.getValue();
        if (Email == null) return -1;

        // Prosta validacija, prava je teža za napisati i gledati
        if (!Email.matches("[\\w\\-.]+@[\\w\\-.]+\\.[A-Za-z]+")) {
            return R.string.email_invalid;
        }
        return -1;
    }

    public int validatePassword() {
        String Password = password.getValue();
        if (Password == null) return -1;
        if (Password.length() < 8) {
            return R.string.password_too_short;
        }
        return -1;
    }

    public int validateRepeatPassword() {
        String RepeatPassword = repeatPassword.getValue();
        if (RepeatPassword == null) return -1;
        if (!RepeatPassword.equals(password.getValue())) {
            return R.string.password_mismatch;
        }
        return -1;
    }

    public int setUsername(String s) {
        username.setValue(s);
        return validateUsername();
    }

    public int setEmail(String s) {
        email.setValue(s);
        return validateEmail();
    }

    public int setPassword(String s) {
        password.setValue(s);
        return validatePassword();
    }

    public int setRepeatPassword(String s) {
        repeatPassword.setValue(s);
        return validateRepeatPassword();
    }

    public boolean OnRegisterButtonPressed() {
        if ((validateUsername() != -1) || (validateEmail() != -1) || (validatePassword() != -1) || (validateRepeatPassword() != -1)) {
            return false;
        }
        return true;
    }
}
