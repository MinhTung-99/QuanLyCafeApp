package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class ProductHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productManage";
    private static final String TABLE_PRODUCT = "products";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_UNIT = "unit";
    private static final String KEY_PRICE = "price";
    private static final String KEY_SALE = "sale";
    private static final String KEY_AVAILABLE_QUANTITY = "availableQuantity";
    private static final String KEY_SPECIES = "species";
    private static final String KEY_BARCODE = "barcode";
    public ProductHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT," +
                KEY_IMAGE + " BLOB," +
                KEY_UNIT + " TEXT," +
                KEY_PRICE + " REAL," +
                KEY_SALE + " TEXT," +
                KEY_AVAILABLE_QUANTITY + " INTEGER," +
                KEY_SPECIES + " TEXT," +
                KEY_BARCODE + " INTEGER)";
        db.execSQL(CREATE_PRODUCT_TABLE);
    }
    public void addProduct(Product product) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME,product.getName());
            values.put(KEY_IMAGE,product.getImageByteArr());
            values.put(KEY_UNIT, product.getUnit());
            values.put(KEY_PRICE,product.getPrice());
            values.put(KEY_SALE, product.getSale());
            values.put(KEY_AVAILABLE_QUANTITY, product.getAvailableQuantity());
            values.put(KEY_SPECIES, product.getSpecies());
            values.put(KEY_BARCODE, product.getBarcode());
            db.insert(TABLE_PRODUCT,null,values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setId(cursor.getLong(0));
                product.setName(cursor.getString(1));
                product.setImageByteArr(cursor.getBlob(2));
                product.setUnit(cursor.getString(3));
                product.setPrice(cursor.getFloat(4));
                product.setSale(cursor.getString(5));
                product.setAvailableQuantity(cursor.getInt(6));
                product.setSpecies(cursor.getString(7));
                product.setBarcode(cursor.getInt(8));
                products.add(product);
            } while(cursor.moveToNext());
        }
        return products;
    }
    public int updateProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,product.getName());
        values.put(KEY_IMAGE,product.getImageByteArr());
        values.put(KEY_UNIT,product.getUnit());
        values.put(KEY_PRICE,product.getPrice());
        values.put(KEY_SALE, product.getSale());
        values.put(KEY_AVAILABLE_QUANTITY, product.getAvailableQuantity());
        values.put(KEY_SPECIES, product.getSpecies());
        values.put(KEY_BARCODE, product.getBarcode());
        return db.update(TABLE_PRODUCT,
                values,
                KEY_ID+"=?",
                new String[]{String.valueOf(product.getId())});
    }
    public int deleteProduct(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PRODUCT,
                KEY_ID+"=?",
                new String[]{String.valueOf(id)});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
