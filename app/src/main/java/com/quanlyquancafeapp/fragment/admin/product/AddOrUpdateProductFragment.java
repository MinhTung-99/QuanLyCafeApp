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
import com.quanlyquancafeapp.utils.KeyboardUtils;
import com.quanlyquancafeapp.view.admin.IAddOrUpdateView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddOrUpdateProductFragment extends Fragment implements View.OnClickListener, IAddOrUpdateView {
    private FragmentAddOrUpdateProductBinding binding;
    private AddOrUpdateProductPresenter addOrUpdatePresenter;
    private byte[] bytes;
    Product product;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_or_update_product, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initAction();

        if(this.product.getSpecies().equals("CAFE")){
            binding.imgProduct.setImageResource(R.drawable.ic_cafe);
        }else if(this.product.getSpecies().equals("DRINK")){
            binding.imgProduct.setImageResource(R.drawable.ic_drink);
        }

        if(!product.isAdd()){
            setTextEdt();
            setImgImageView();
            setTextBtn();
        }
    }
    private void initAction() {
        binding.btnAddOrUpdate.setOnClickListener(this);
        binding.imgProduct.setOnClickListener(this);
    }
    private Product setProduct(){
        Product product = new Product();
        product.setId(this.product.getId());
        product.setName(binding.edtName.getText().toString());
        product.setImageByteArr(bytes);
        product.setUnit(binding.edtUnit.getText().toString());
        product.setPrice(Float.parseFloat(binding.edtPrice.getText().toString()));
        product.setSale(binding.edtSale.getText().toString());
        product.setAvailableQuantity(Integer.parseInt(binding.edtAvailableQuantity.getText().toString()));
        product.setBarcode(Integer.parseInt(binding.edtBarcode.getText().toString()));
        if(this.product.getSpecies().equals("CAFE")){
            product.setSpecies("CAFE");
        }else if(this.product.getSpecies().equals("DRINK")){
            product.setSpecies("DRINK");
        }
        if(this.product.getImageByteArr() != null){
            product.setImageByteArr(this.product.getImageByteArr());
        }else {
            product.setImageByteArr(bytes);
        }
        return product;
    }
    private void addOrUpdateProduct(){
        Product product = setProduct();
        if(this.product.getSpecies().equals("CAFE")){
            if(this.product.isAdd()){
                addOrUpdatePresenter.addProductDB(product);
            }else {
                addOrUpdatePresenter.updateProductDB(product);
            }
        }else if(this.product.getSpecies().equals("DRINK")){
            if(this.product.isAdd()){
                addOrUpdatePresenter.addProductDB(product);
            }else {
                addOrUpdatePresenter.updateProductDB(product);
            }
        }
    }
    private void init() {
        addOrUpdatePresenter = new AddOrUpdateProductPresenter(this,getContext());
        product = (Product) getArguments().getSerializable("product");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                binding.imgProduct.setImageURI(imageUri);
                InputStream iStream = getActivity().getContentResolver().openInputStream(imageUri);
                this.bytes = addOrUpdatePresenter.getBytes(iStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void setTextEdt() {
        binding.edtName.setText(product.getName());
        binding.edtUnit.setText(product.getUnit());
        binding.edtPrice.setText(String.valueOf(product.getPrice()));
        binding.edtSale.setText(String.valueOf(product.getSale()));
        binding.edtAvailableQuantity.setText(String.valueOf(product.getAvailableQuantity()));
        binding.edtBarcode.setText(String.valueOf(product.getBarcode()));
        binding.txtUnit.setText(product.getUnit());
    }
    @Override
    public void setImgImageView() {
        binding.imgProduct.setImageBitmap(product.getImageBitmap());
    }
    @Override
    public void setTextBtn() {
        binding.btnAddOrUpdate.setText("Cập nhập");
    }
    @Override
    public void takePhoto() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1001);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_or_update:
                addOrUpdateProduct();
                Navigation.findNavController(v).popBackStack();
                KeyboardUtils.hideKeyboard(getActivity());
                break;
            case R.id.img_product:
                takePhoto();
                break;
        }
    }
}
