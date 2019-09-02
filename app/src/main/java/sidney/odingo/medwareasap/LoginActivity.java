package sidney.odingo.medwareasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;
import sidney.odingo.medwareasap.Model.Users;
import sidney.odingo.medwareasap.Prevalent.Prevalent;

public class LoginActivity extends AppCompatActivity {

    private EditText PhoneNumber;
    private EditText Password;

    private Button Login;
    private ProgressDialog loadingBar;
    private TextView AdminLink,NotAdminLink;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login = findViewById(R.id.login_btn);
        PhoneNumber = findViewById(R.id.phone_number_input);
        Password = findViewById(R.id.phone_password_input);
        AdminLink = findViewById(R.id.admin_panel_link);
        NotAdminLink = findViewById(R.id.not_admin_panel_link);
        loadingBar = new ProgressDialog(this);

        chkBoxRememberMe = findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Login.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });


    }

    private void LoginUser() {

        String phone = PhoneNumber.getText().toString();
        String password = Password.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "enter phoneNumber", Toast.LENGTH_SHORT);
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT);
        } else {

            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("checking Credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone, password);

        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {

        if (chkBoxRememberMe.isChecked())
        {
          Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);


        }

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentDbName).child(phone).exists()) {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if (usersData.getPhone().equals(phone)) {

                        if (usersData.getPassword().equals(password)) {
                            if (parentDbName.equals("Admins"))
                            {
                                Toast.makeText(LoginActivity.this, " Welcome Admin, You're Login Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                if (parentDbName.equals("Users"))
                                {
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    Prevalent.currentOnlineUsers=usersData;
                                    startActivity(intent);
                                }
                            }
                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "password is incorrect", Toast.LENGTH_SHORT).show();

                        }
                    }


                } else {

                    Toast.makeText(LoginActivity.this, "wrong phone Number or password", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
