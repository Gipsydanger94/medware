package sidney.odingo.medwareasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText Name, PhoneNumber, Password;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        CreateAccountButton = findViewById(R.id.register_btn);
        Name = findViewById(R.id.register_username_input);
        PhoneNumber = findViewById(R.id.register_phone_number_input);
        Password = findViewById(R.id.register_password_input);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {

        String name = Name.getText().toString();
        String phone = PhoneNumber.getText().toString();
        String password = Password.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "enter Name", Toast.LENGTH_SHORT);
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "enter phoneNumber", Toast.LENGTH_SHORT);
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT);
        } else {

            loadingBar.setTitle("create Account");
            loadingBar.setMessage("checking Credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Validatephone(name, phone, password);
        }
    }

    private void Validatephone(final String name, final String phone, final String password) {
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!(dataSnapshot.child("Users").child(phone).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("name", name);
                    userdataMap.put("password", password);

                    Rootref.child("Users").child(phone).updateChildren(userdataMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(RegistrationActivity.this,"your account has been Created",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }

                            else
                            {
                                loadingBar.dismiss();
                              Toast.makeText(RegistrationActivity.this,"Network Error please try again",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                } else {

                    Toast.makeText(RegistrationActivity.this, "This" + phone + "already exists.", Toast.LENGTH_SHORT);
                    loadingBar.dismiss();
                    Toast.makeText(RegistrationActivity.this, "please try another Number", Toast.LENGTH_SHORT);

                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

