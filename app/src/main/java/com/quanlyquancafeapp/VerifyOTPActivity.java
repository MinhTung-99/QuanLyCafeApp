package com.quanlyquancafeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.quanlyquancafeapp.databinding.ActivityVerifyOTPBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.presenter.admin.VerifyOTPPresenter;
import com.quanlyquancafeapp.utils.Constance;

public class VerifyOTPActivity extends AppCompatActivity {
    private ActivityVerifyOTPBinding binding;
    private FirebaseAuth auth;
    private VerifyOTPPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_verify_o_t_p);
        presenter = new VerifyOTPPresenter(this);

        setupOTPEdt();

        String verificationId = getIntent().getStringExtra("verificationId");
        auth = FirebaseAuth.getInstance();
        binding.btnVerify.setOnClickListener(v->{
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,"123456");
            authenticationUser(credential);
        });
    }
    private void setupOTPEdt(){
        binding.edtVerify1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    binding.edtVerify2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.edtVerify2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    binding.edtVerify3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.edtVerify3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    binding.edtVerify4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.edtVerify4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    binding.edtVerify5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.edtVerify5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    binding.edtVerify6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void authenticationUser(PhoneAuthCredential authCredential){
        auth.signInWithCredential(authCredential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //Toast.makeText(VerifyOTPActivity.this,"SUCCESS"+authResult.getUser().getUid(),Toast.LENGTH_SHORT).show();
                presenter.handleIntent(VerifyOTPActivity.this, authResult);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constance.REQUEST_CODE){
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result","back");
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }
}