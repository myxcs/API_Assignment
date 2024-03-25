package com.ph36461.api_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ph36461.api_assignment.model.UserModel;

public class RegisterScreen extends AppCompatActivity {


    private FirebaseAuth auth;
    private FirebaseDatabase database;

    private DatabaseReference reference;

    private UserModel userModel;

    private EditText signupEmail, signupPassword, signupName, signupPhone;
    private Button signupBtn;
    private TextView loginRedirectText;
    private ImageButton btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        signupName = findViewById(R.id.ed_name);
        signupEmail = findViewById(R.id.ed_email);
        signupPassword = findViewById(R.id.ed_password);
        signupPhone = findViewById(R.id.ed_phone);
        signupBtn = findViewById(R.id.btn_register);
        loginRedirectText = findViewById(R.id.tv_login);
        btnBack = findViewById(R.id.bt_back);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });



        loginRedirectText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });




    }

    private void createUser() {
        {

            //tạo user
            String userMail = signupEmail.getText().toString();
            String userPass = signupPassword.getText().toString();
            String userName = signupName.getText().toString();
            String userPhone = signupPhone.getText().toString();


            //ktra nhập
            if (TextUtils.isEmpty(userName)) {
                signupName.setError("Tên người dùng không được để trống");
                return;
            }
            if(TextUtils.isEmpty(userMail)){
                signupEmail.setError("Email không được để trống");
                return;
            }
            if(TextUtils.isEmpty(userPass)){
                signupPassword.setError("Mật khẩu không được để trống");
                return;

            }
            if(userPass.length() < 8){
                signupPassword.setError("Mật khẩu ít nhất có 8 kí tự");
                return;
            }
            else {
                auth.createUserWithEmailAndPassword(userMail, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //   set data user lên firebase
                            UserModel userModel = new UserModel(userName, userMail, userPhone, userPass,"",0,false);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(String.valueOf(id)).setValue(userModel);

                            //để push dữ liệu từng cái một, khá thú vị nên để lại
//                        reference.child("User").child(String.valueOf(id)).setValue(userModel);
//                        reference.child("User").child(String.valueOf(userModel.getUserId())).setValue(userModel);
//                        database.getReference().child("Users").child(String.valueOf(id)).setValue(userModel);
//                        reference.push().setValue(userModel);

                            Toast.makeText(RegisterScreen.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
                        }
                        else {
                            Toast.makeText(RegisterScreen.this, "Đăng kí thất bại: " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}