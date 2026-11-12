package rs.ac.uns.ftn.radnickirecepti.ui.user_creation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import rs.ac.uns.ftn.radnickirecepti.R;
import rs.ac.uns.ftn.radnickirecepti.databinding.FragmentCreateUserBinding;

public class CreateUserFragment extends Fragment {
    private FragmentCreateUserBinding binding;
    private CreateUserViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateUserBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CreateUserViewModel.class);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CreateUserViewModel.class);

        binding.usernameTextbox.addTextChangedListener(new TextWatcher() {
            @Override public void afterTextChanged(Editable s) {
                int err = viewModel.setUsername(s.toString());
                if (err != -1) {
                    binding.usernameTextbox.setError(getString(err));
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        binding.emailTextbox.addTextChangedListener(new TextWatcher() {
            @Override public void afterTextChanged(Editable s) {
                int err = viewModel.setEmail(s.toString());
                if (err != -1) {
                    binding.emailTextbox.setError(getString(err));
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        binding.passwordTextbox.addTextChangedListener(new TextWatcher() {
            @Override public void afterTextChanged(Editable s) {
                int err = viewModel.setPassword(s.toString());
                if (err != -1) {
                    binding.passwordTextbox.setError(getString(err));
                }

                err = viewModel.validateRepeatPassword();
                if (err != -1) {
                    binding.repeatPasswordTextbox.setError(getString(err));
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        binding.repeatPasswordTextbox.addTextChangedListener(new TextWatcher() {
            @Override public void afterTextChanged(Editable s) {
                int err = viewModel.setRepeatPassword(binding.repeatPasswordTextbox.getEditableText().toString());
                if (err != -1) {
                    binding.repeatPasswordTextbox.setError(getString(err));
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        binding.registerButton.setOnClickListener(v -> {
            binding.registerButton.setVisibility(View.GONE);
            binding.loading.setVisibility(View.VISIBLE);
            if (viewModel.OnRegisterButtonPressed()) {
                NavHostFragment.findNavController(this).navigate(R.id.action_CreateUser_to_MainPage);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}