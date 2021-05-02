package com.quanlyquancafeapp.fragment.admin.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.quanlyquancafeapp.utils.ToastUtils;
import com.quanlyquancafeapp.view.admin.IAddOrUpdateView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddOrUpdateProductFragment extends Fragment implements View.OnClickListener, IAddOrUpdateView {
    private FragmentAddOrUpdateProductBinding binding;
    private AddOrUpdateProductPresenter addOrUpdatePresenter;
    private byte[] bytes;
    private Product product;
    private boolean isNameProduct = true, isUnit = true, isPrice = true, isSale = true, isAvailableQuantity = true;
    private Boolean isRemember;
    private String nameProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_or_update_product, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isRemember = true;

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
            binding.btnAddOrUpdate.setEnabled(true);
            bytes = product.getImageByteArr();

            if(isRemember){
                nameProduct = product.getName();
                isRemember = false;
            }
        }

        listenEdt();
    }
    private void listenEdt(){
        binding.edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isNameProduct = true;
                }else {
                    isNameProduct = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameProduct && isUnit && isPrice && isSale && isAvailableQuantity && bytes != null){
                    binding.btnAddOrUpdate.setEnabled(true);
                }else {
                    binding.btnAddOrUpdate.setEnabled(false);
                }
            }
        });
        binding.edtUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isUnit = true;
                }else {
                    isUnit = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameProduct && isUnit && isPrice && isSale && isAvailableQuantity && bytes  != null){
                    binding.btnAddOrUpdate.setEnabled(true);
                }else {
                    binding.btnAddOrUpdate.setEnabled(false);
                }
            }
        });
        binding.edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isPrice = true;
                }else {
                    isPrice = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameProduct && isUnit && isPrice && isSale && isAvailableQuantity && bytes != null){
                    binding.btnAddOrUpdate.setEnabled(true);
                }else {
                    binding.btnAddOrUpdate.setEnabled(false);
                }
            }
        });
        binding.edtSale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isSale = true;
                }else {
                    isSale = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameProduct && isUnit && isPrice && isSale && isAvailableQuantity && bytes != null){
                    binding.btnAddOrUpdate.setEnabled(true);
                }else {
                    binding.btnAddOrUpdate.setEnabled(false);
                }
            }
        });
        binding.edtAvailableQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isAvailableQuantity = true;
                }else {
                    isAvailableQuantity = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameProduct && isUnit && isPrice && isSale && isAvailableQuantity && bytes != null){
                    binding.btnAddOrUpdate.setEnabled(true);
                }else {
                    binding.btnAddOrUpdate.setEnabled(false);
                }
            }
        });
//        binding.edtBarcode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.toString().length() > 0){
//                    isBarcode = true;
//                }else {
//                    isBarcode = false;
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(isNameProduct && isUnit && isPrice && isSale && isAvailableQuantity && isBarcode && bytes != null){
//                    binding.btnAddOrUpdate.setEnabled(true);
//                }else {
//                    binding.btnAddOrUpdate.setEnabled(false);
//                }
//            }
//        });
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
        product.setBarcode(0);

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
                if(addOrUpdatePresenter.isSameProduct(product.getName())){
                    ToastUtils.showToast(getActivity(), "Tên sản phẩm đã tồn tại");
                }else {
                    addOrUpdatePresenter.addProductDB(product);
                    Navigation.findNavController(getView()).popBackStack();
                }
            }else {
                if(addOrUpdatePresenter.isSameProduct(product.getName()) && !product.getName().toLowerCase().equals(nameProduct.toLowerCase())){
                    ToastUtils.showToast(getActivity(), "Tên sản phẩm đã tồn tại");
                }else {
                    addOrUpdatePresenter.updateProductDB(product);
                    Navigation.findNavController(getView()).popBackStack();
                }
            }
        }else if(this.product.getSpecies().equals("DRINK")){
            if(this.product.isAdd()){
                if(addOrUpdatePresenter.isSameProduct(product.getName())){
                    ToastUtils.showToast(getActivity(), "Tên sản phẩm đã tồn tại");
                }else {
                    addOrUpdatePresenter.addProductDB(product);
                    Navigation.findNavController(getView()).popBackStack();
                }
            }else {
                if(addOrUpdatePresenter.isSameProduct(product.getName()) && !product.getName().toLowerCase().equals(nameProduct.toLowerCase())){
                    ToastUtils.showToast(getActivity(), "Tên sản phẩm đã tồn tại");
                }else {
                    addOrUpdatePresenter.updateProductDB(product);
                    Navigation.findNavController(getView()).popBackStack();
                }
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

                if(isNameProduct && isUnit && isPrice && isSale && isAvailableQuantity && bytes != null){
                    binding.btnAddOrUpdate.setEnabled(true);
                }else {
                    binding.btnAddOrUpdate.setEnabled(false);
                }
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
                KeyboardUtils.hideKeyboard(getActivity());
                break;
            case R.id.img_product:
                takePhoto();
                break;
        }
    }
}
