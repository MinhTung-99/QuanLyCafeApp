package com.quanlyquancafeapp.fragment.admin.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentAddOrUpdateProductBinding;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.presenter.admin.product.AddOrUpdateProductPresenter;
import com.quanlyquancafeapp.view.IAddOrUpdateView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddOrUpdateProductFragment extends Fragment implements IAddOrUpdateView {
    private FragmentAddOrUpdateProductBinding binding;
    private AddOrUpdateProductPresenter addOrUpdatePresenter;
    private byte[] bytes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_or_update_product, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addOrUpdatePresenter = new AddOrUpdateProductPresenter(this,getContext());
        Product product = (Product) getArguments().getSerializable("product");
        if(!product.isAdd()){
            binding.edtName.setText(product.getName());
            binding.image.setImageBitmap(product.getImageBitmap());
            binding.edtUnit.setText(product.getUnit());
            binding.edtPrice.setText(String.valueOf(product.getPrice()));
            binding.edtAvailableQuantity.setText(String.valueOf(product.getAvailableQuantity()));
            binding.edtBarcode.setText(String.valueOf(product.getBarcode()));
            binding.txtUnit.setText(product.getUnit());
        }
        binding.btnAddOrUpdate.setOnClickListener(v->{
            Product myProduct = new Product(product.getId(),binding.edtName.getText().toString(), bytes, binding.edtUnit.getText().toString(),
                    Float.parseFloat(binding.edtPrice.getText().toString()), binding.edtSale.getText().toString(),
                    Integer.parseInt(binding.edtAvailableQuantity.getText().toString()),Integer.parseInt(binding.edtBarcode.getText().toString()));

            if(product.getSpecies().equals("CAFE")){
                myProduct.setSpecies("CAFE");
                if(product.isAdd()){
                    addOrUpdatePresenter.addProductDB(myProduct);
                }else {
                    if(product.getImageByteArr() != null){
                        myProduct.setImageByteArr(product.getImageByteArr());
                    }else {
                        myProduct.setImageByteArr(bytes);
                    }
                    addOrUpdatePresenter.updateProductDB(myProduct);
                }
            }else if(product.getSpecies().equals("DRINK")){
                myProduct.setSpecies("DRINK");
                if(product.isAdd()){
                    addOrUpdatePresenter.addProductDB(myProduct);
                }else {
                    if(product.getImageByteArr() != null){
                        myProduct.setImageByteArr(product.getImageByteArr());
                    }else {
                        myProduct.setImageByteArr(bytes);
                    }
                    addOrUpdatePresenter.updateProductDB(myProduct);
                }
            }
            Navigation.findNavController(view).popBackStack();
        });
        binding.image.setOnClickListener(v->{
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1001);
        });
    }
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
