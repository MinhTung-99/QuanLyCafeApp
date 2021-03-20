package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cafeManage";

    //INVOICE TABLE
    private static final String TABLE_INVOICE = "invoices";
    private static final String KEY_ID_INVOICE = "id";
    private static final String KEY_ID_ACCOUNT = "id_account";
    private static final String KEY_ID_PRODUCT = "id_product";
    private static final String KEY_ID_TABLE = "id_table";
    private static final String KEY_COUNT = "count";
    private static final String KEY_TOTAL_MONEY = "total_money";
    private static final String KEY_INTO_MONEY = "into_money";
    private static final String KEY_DATE = "date";
    private static final String KEY_PAYED = "payed";

    //USER TABLE
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID_USER = "id";
    private static final String KEY_NAME_STORE = "name_store";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TYPE_USER = "type_user";

    //TABLES TABLE
    private static final String TABLE_TABLES = "tables";
    private static final String KEY_ID_TABLES = "id";
    private static final String KEY_NAME_TABLE = "name";

    //PRODUCT TABLE
    private static final String TABLE_PRODUCT = "products";
    private static final String KEY_ID_PRODUCTS = "id";
    private static final String KEY_NAME_PRODUCT = "name";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_UNIT = "unit";
    private static final String KEY_PRICE = "price";
    private static final String KEY_SALE = "sale";
    private static final String KEY_AVAILABLE_QUANTITY = "availableQuantity";
    private static final String KEY_SPECIES = "species";
    private static final String KEY_BARCODE = "barcode";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                KEY_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME_STORE + " TEXT," +
                KEY_ADDRESS + " TEXT," +
                KEY_PHONE_NUMBER + " TEXT," +
                KEY_GENDER + " TEXT," +
                KEY_USERNAME + " TEXT," +
                KEY_PASSWORD + " TEXT," +
                KEY_TYPE_USER + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_TABLES_TABLE = "CREATE TABLE " + TABLE_TABLES + " (" +
                KEY_ID_TABLES + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME_TABLE + " TEXT)";
        db.execSQL(CREATE_TABLES_TABLE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + " (" +
                KEY_ID_PRODUCTS + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME_PRODUCT + " TEXT," +
                KEY_IMAGE + " BLOB," +
                KEY_UNIT + " TEXT," +
                KEY_PRICE + " REAL," +
                KEY_SALE + " TEXT," +
                KEY_AVAILABLE_QUANTITY + " INTEGER," +
                KEY_SPECIES + " TEXT," +
                KEY_BARCODE + " INTEGER)";
        db.execSQL(CREATE_PRODUCT_TABLE);

        String CREATE_TABLE_INVOICE = "CREATE TABLE "
                + TABLE_INVOICE + " ("
                + KEY_ID_INVOICE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_ID_ACCOUNT + " INTEGER,"
                + KEY_ID_PRODUCT + " INTEGER,"
                + KEY_ID_TABLE + " INTEGER,"
                + KEY_COUNT + " INTEGER,"
                + KEY_TOTAL_MONEY + " REAL,"
                + KEY_INTO_MONEY + " REAL,"
                + KEY_DATE + " TEXT,"
                + KEY_PAYED + " INTEGER,"
                + " FOREIGN KEY ("+KEY_ID_ACCOUNT+") REFERENCES "+TABLE_USERS+"("+KEY_ID_USER+"),"
                + " FOREIGN KEY ("+KEY_ID_PRODUCT+") REFERENCES "+TABLE_PRODUCT+"("+KEY_ID_PRODUCT+"),"
                + " FOREIGN KEY ("+KEY_ID_TABLE+") REFERENCES "+TABLE_TABLES+"("+KEY_ID_TABLES+"));";
        db.execSQL(CREATE_TABLE_INVOICE);
    }
    public void addUser(User user) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME_STORE,user.getNameStore());
            values.put(KEY_ADDRESS,user.getAddress());
            values.put(KEY_PHONE_NUMBER, user.getPhoneNumber());
            values.put(KEY_GENDER,user.getGender());
            values.put(KEY_USERNAME, user.getUserName());
            values.put(KEY_PASSWORD, user.getPassword());
            values.put(KEY_TYPE_USER, user.getTypeUser());
            db.insert(TABLE_USERS,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(cursor.getLong(0));
                user.setNameStore(cursor.getString(1));
                user.setAddress(cursor.getString(2));
                user.setPhoneNumber(cursor.getString(3));
                user.setGender(cursor.getString(4));
                user.setUserName(cursor.getString(5));
                user.setPassword(cursor.getString(6));
                user.setTypeUser(cursor.getString(7));
                users.add(user);
            } while(cursor.moveToNext());
        }
        return users;
    }
    public void addProduct(Product product) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME_PRODUCT,product.getName());
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
    public int updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME,user.getUserName());
        values.put(KEY_PHONE_NUMBER,user.getPhoneNumber());
        values.put(KEY_GENDER,user.getGender());
        values.put(KEY_PASSWORD,user.getPassword());
        return db.update(TABLE_USERS,
                values,
                KEY_ID_USER+"=?",
                new String[]{String.valueOf(user.getId())});
    }
    public int deleteUser(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USERS,
                KEY_ID_USER+"=?",
                new String[]{String.valueOf(id)});
    }
    public int updateProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_PRODUCT,product.getName());
        values.put(KEY_IMAGE,product.getImageByteArr());
        values.put(KEY_UNIT,product.getUnit());
        values.put(KEY_PRICE,product.getPrice());
        values.put(KEY_SALE, product.getSale());
        values.put(KEY_AVAILABLE_QUANTITY, product.getAvailableQuantity());
        values.put(KEY_SPECIES, product.getSpecies());
        values.put(KEY_BARCODE, product.getBarcode());
        return db.update(TABLE_PRODUCT,
                values,
                KEY_ID_PRODUCTS+"=?",
                new String[]{String.valueOf(product.getId())});
    }
    public int deleteProduct(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PRODUCT,
                KEY_ID_PRODUCTS+"=?",
                new String[]{String.valueOf(id)});
    }
    public void addTable(Table table) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME_TABLE,table.getName());
            db.insert(TABLE_TABLES,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<Table> getTables(){
        ArrayList<Table> tables = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TABLES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Table table = new Table();
                table.setId(cursor.getLong(0));
                table.setName(cursor.getString(1));
                tables.add(table);
            } while(cursor.moveToNext());
        }
        return tables;
    }
    public int updateTable(Table table){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_TABLE,table.getName());
        return db.update(TABLE_TABLES,
                values,
                KEY_ID_TABLES+"=?",
                new String[]{String.valueOf(table.getId())});
    }
    public int deleteTable(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TABLES,
                KEY_ID_TABLES+"=?",
                new String[]{String.valueOf(id)});
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
    public void addInvoice(Invoice invoice) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_ID_ACCOUNT,invoice.getIdAccount());
            values.put(KEY_ID_PRODUCT,invoice.getIdProduct());
            values.put(KEY_ID_TABLE, invoice.getIdTable());
            values.put(KEY_COUNT,invoice.getCount());
            values.put(KEY_TOTAL_MONEY, invoice.getTotalMoney());
            values.put(KEY_INTO_MONEY, invoice.getInToMoney());
            values.put(KEY_DATE, invoice.getDateBuy());
            values.put(KEY_PAYED, invoice.getIsPay());
            db.insert(TABLE_INVOICE,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public int updateInvoice(Invoice invoice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_INVOICE, invoice.getId());
        values.put(KEY_ID_ACCOUNT,invoice.getIdAccount());
        values.put(KEY_ID_PRODUCT,invoice.getIdProduct());
        values.put(KEY_ID_TABLE, invoice.getIdTable());
        values.put(KEY_COUNT,invoice.getCount());
        values.put(KEY_TOTAL_MONEY, invoice.getTotalMoney());
        values.put(KEY_INTO_MONEY, invoice.getInToMoney());
        values.put(KEY_DATE, invoice.getDateBuy());
        values.put(KEY_PAYED, invoice.getIsPay());
        return db.update(TABLE_INVOICE,
                values,
                KEY_ID_INVOICE+"=?",
                new String[]{String.valueOf(invoice.getId())});
    }
    public int deleteInvoice(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_INVOICE,
                KEY_ID_INVOICE+"=?",
                new String[]{String.valueOf(id)});
    }
    public ArrayList<Invoice> getInvoices(){
        ArrayList<Invoice> invoices = new ArrayList<>();
        String selectQuery = "SELECT * FROM invoices iv INNER JOIN users u ON iv.id_account=u.id"
                //+" INNER JOIN tables t ON iv.id_table=t.id"
                +" INNER JOIN products p ON iv.id_product=p.id";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Invoice invoice = new Invoice();
                invoice.setId(cursor.getLong(0));
                invoice.setIdAccount(cursor.getLong(1));
                invoice.setIdProduct(cursor.getLong(2));
                invoice.setIdTable(cursor.getLong(3));
                invoice.setCount(cursor.getInt(4));
                invoice.setTotalMoney(cursor.getFloat(5));
                invoice.setInToMoney(cursor.getFloat(6));
                invoice.setDateBuy(cursor.getString(7));
                invoice.setIsPay(cursor.getInt(8));
                invoices.add(invoice);
            } while(cursor.moveToNext());
        }
        return invoices;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
