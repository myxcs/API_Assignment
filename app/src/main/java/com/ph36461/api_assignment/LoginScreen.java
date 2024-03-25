package com.ph36461.api_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginScreen extends AppCompatActivity {


    private FirebaseAuth auth;
    private DatabaseReference reference;
    public static final String SHARED_PREFS = "sharedPrefs";

    private EditText loginEmail, loginPassword;
    private CheckBox rememberMe;
    private String remember;
    private ImageButton btBack;
    private Button btnLogin;
    private TextView signupRedirectText;
    boolean banStatus;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.ed_email);
        loginPassword = findViewById(R.id.ed_password);
        // rememberMe = findViewById(R.id.checkbox);
        btnLogin = findViewById(R.id.btn_login);
        signupRedirectText = findViewById(R.id.tv_register);
        btBack = findViewById(R.id.bt_back);


        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        // checkBanStatus();
                                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(LoginScreen.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginScreen.this, "Đăng nhập thất bại, vui lòng kiểm tra lại email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } else {
                        loginPassword.setError("Vui lòng nhập lại mật khẩu");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Vui lòng nhập email");
                } else {
                    loginEmail.setError("Vui lòng điền đầy đủ email");
                }
            }

        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
                finish();
            }
        });




    }
}