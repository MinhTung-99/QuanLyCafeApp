package com.quanlyquancafeapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentAddOrUpdateStoreBinding;
import com.quanlyquancafeapp.presenter.AddOrUpdatePresenter;
import com.quanlyquancafeapp.view.IAddOrUpdateView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AddOrUpdateStoreFragment extends Fragment implements IAddOrUpdateView {
    private FragmentAddOrUpdateStoreBinding binding;
    private AddOrUpdatePresenter addOrUpdatePresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_or_update_store, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addOrUpdatePresenter = new AddOrUpdatePresenter(this,getContext());
        String species = getArguments().getString("species");
        if(species.equals("CAFE")){
            binding.edtUnit.setVisibility(View.GONE);
            binding.edtAvailableQuantity.setVisibility(View.GONE);
            binding.edtBarcode.setVisibility(View.GONE);
        }else if(species.equals("DRINK")){
            binding.edtUnit.setVisibility(View.VISIBLE);
            binding.edtAvailableQuantity.setVisibility(View.VISIBLE);
            binding.edtBarcode.setVisibility(View.VISIBLE);
        }
        binding.btnAdd.setOnClickListener(v->{
            if(binding.edtUnit.getText().toString().equals("") && binding.edtUnit.getText().toString().equals("") &&
                    binding.edtAvailableQuantity.getText().toString().equals("") && binding.edtBarcode.getText().toString().equals("")){

                addOrUpdatePresenter.addProductDB(binding.edtName.getText().toString(), bytes, "NULL",
                        Float.parseFloat(binding.edtPrice.getText().toString()), 0,
                        "CAFE",0);
            }else {
                addOrUpdatePresenter.addProductDB(binding.edtName.getText().toString(), bytes, binding.edtUnit.getText().toString(),
                        Float.parseFloat(binding.edtPrice.getText().toString()), Integer.parseInt(binding.edtAvailableQuantity.getText().toString()),
                        "DRINK",Integer.parseInt(binding.edtBarcode.getText().toString()));
            }
            Navigation.findNavController(view).popBackStack();
        });
        binding.image.setOnClickListener(v->{
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1001);
        });
    }
    private byte[] bytes;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                binding.image.setImageURI(imageUri);
                InputStream iStream = getActivity().getContentResolver().openInputStream(imageUri);
                byte[] bytes = getBytes(iStream);
                this.bytes = bytes;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
