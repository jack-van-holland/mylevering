package com.example.mylevering;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountFrag extends Fragment {

    private FirebaseAuth auth;
    private FirebaseUser user;

    private TextView name, email, password;
    private Button applyChanges;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        final MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.setTitle("My Account");
        }

        name = view.findViewById(R.id.enter_name);
        email = view.findViewById(R.id.enter_email);
        //password = view.findViewById(R.id.edit_password);
        applyChanges = view.findViewById(R.id.submit);

        name.setText(auth.getCurrentUser().getDisplayName());
        email.setText(auth.getCurrentUser().getEmail());

        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals(user.getDisplayName())) {
                    if (name.getText().toString().equals("")) {
                        String notFilled = "Name cannot be empty";
                        Toast.makeText(getContext(), notFilled, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name.getText().toString())
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        name.setText(user.getDisplayName());
                                        NavigationView navigationView = main.findViewById(R.id.nav_view);
                                        View headerView = navigationView.getHeaderView(0);
                                        TextView headerName = headerView.findViewById(R.id.id_name);
                                        headerName.setText(user.getDisplayName());

                                        Toast.makeText(getContext(), "Changes Applied", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "Name Update Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                if (email.getText() != user.getEmail()) {
                    if (!email.getText().toString().contains("@jhu.edu")) {
                        String invalidEmail = "Invalid JHU email";
                        Toast.makeText(getContext(), invalidEmail, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    user.updateEmail(email.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        email.setText(user.getEmail());
                                        Toast.makeText(getContext(), "Changes Applied", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "Email Update Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                /*
                user.updatePassword(password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Log.d(TAG, "User password updated.");
                                }
                            }
                        });
                 */
            }
        });

        return view;
    }
}
