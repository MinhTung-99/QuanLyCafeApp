package com.quanlyquancafeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.quanlyquancafeapp.databinding.ActivitySendOTPBinding;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.utils.KeyboardUtils;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {
    private ActivitySendOTPBinding binding;

    private PhoneAuthProvider.ForceResendingToken token;
    private FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_send_o_t_p);

        String text = getIntent().getStringExtra("data");
        binding.btnRegister.setText(text);

        binding.btnRegister.setOnClickListener(v->{
            verifyPhoneNumber(binding.txtCountry.getText().toString() + binding.edtPhoneNumber.getText().toString());
            //KeyboardUtils.hideKeyboard(this);
        });

        auth = FirebaseAuth.getInstance();
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                //authenticationUser(phoneAuthCredential); //user for real phone number
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.d("KMFG",e.getMessage());
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
                token = forceResendingToken;

                Intent intent = new Intent(SendOTPActivity.this, VerifyOTPActivity.class);
                intent.putExtra("verificationId", verificationId);
                startActivityForResult(intent, Constance.REQUEST_CODE);
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }
        };
    }

    private void verifyPhoneNumber(String phoneNumber){
        //send OTP
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setActivity(this)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }
//    private void authenticationUser(PhoneAuthCredential authCredential){
//        auth.signInWithCredential(authCredential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//                Toast.makeText(SendOTPActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constance.REQUEST_CODE){
            finish();
        }
    }
}