package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cafeManage";

    //DETAIL INVOICE TABLE


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + UserTable.TABLE_NAME + " (" +
                UserTable.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserTable.KEY_NAME_STORE + " TEXT," +
                UserTable.KEY_ADDRESS + " TEXT," +
                UserTable.KEY_PHONE_NUMBER + " TEXT," +
                UserTable.KEY_GENDER + " TEXT," +
                UserTable.KEY_USERNAME + " TEXT," +
                UserTable.KEY_PASSWORD + " TEXT," +
                UserTable.KEY_TYPE_USER + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_TABLES_FURNITURE = "CREATE TABLE " + FurnitureTable.TABLE_NAME + " (" +
                FurnitureTable.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FurnitureTable.KEY_NAME + " TEXT)";
        db.execSQL(CREATE_TABLES_FURNITURE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + ProductTable.TABLE_NAME + " (" +
                ProductTable.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProductTable.KEY_NAME + " TEXT," +
                ProductTable.KEY_IMAGE + " BLOB," +
                ProductTable.KEY_UNIT + " TEXT," +
                ProductTable.KEY_PRICE + " REAL," +
                ProductTable.KEY_SALE + " TEXT," +
                ProductTable.KEY_AVAILABLE_QUANTITY + " INTEGER," +
                ProductTable.KEY_SPECIES + " TEXT," +
                ProductTable.KEY_BARCODE + " INTEGER)";
        db.execSQL(CREATE_PRODUCT_TABLE);

        String CREATE_TABLE_INVOICE = "CREATE TABLE "
                + InvoiceTable.TABLE_NAME + " ("
                + InvoiceTable.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + InvoiceTable.KEY_ID_USER + " INTEGER,"
                + InvoiceTable.KEY_ID_PRODUCT + " INTEGER,"
                + InvoiceTable.KEY_ID_TABLE + " INTEGER,"
                + InvoiceTable.KEY_TOTAL_MONEY + " REAL,"
                + InvoiceTable.KEY_INTO_MONEY + " REAL,"
                + InvoiceTable.KEY_DATE + " TEXT,"
                + InvoiceTable.KEY_TYPE_PAY + " TEXT,"
                + InvoiceTable.KEY_PAYED + " INTEGER,"
                + " FOREIGN KEY ("+InvoiceTable.KEY_ID_USER+") REFERENCES "+UserTable.TABLE_NAME+"("+UserTable.KEY_ID+"),"
                + " FOREIGN KEY ("+InvoiceTable.KEY_ID_TABLE+") REFERENCES "+FurnitureTable.TABLE_NAME+"("+FurnitureTable.KEY_ID+"));";
        db.execSQL(CREATE_TABLE_INVOICE);

        String CREATE_TABLES_DETAIL_INVOICE = "CREATE TABLE " + InvoiceDetailTable.TABLE_NAME + " (" +
                InvoiceDetailTable.KEY_ID_INVOICE + " INTEGER," +
                InvoiceDetailTable.KEY_ID_PRODUCT + " INTEGER,"+
                InvoiceDetailTable.KEY_COUNT + " INTEGER,"+
                " FOREIGN KEY ("+ InvoiceDetailTable.KEY_ID_INVOICE+") REFERENCES "+InvoiceTable.TABLE_NAME+"("+InvoiceTable.KEY_ID+"),"
                + " FOREIGN KEY ("+ InvoiceDetailTable.KEY_ID_PRODUCT+") REFERENCES "+ProductTable.TABLE_NAME+"("+ProductTable.KEY_ID+"));";
        db.execSQL(CREATE_TABLES_DETAIL_INVOICE);
    }
    public void addUser(User user) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(UserTable.KEY_NAME_STORE,user.getNameStore());
            values.put(UserTable.KEY_ADDRESS,user.getAddress());
            values.put(UserTable.KEY_PHONE_NUMBER, user.getPhoneNumber());
            values.put(UserTable.KEY_GENDER,user.getGender());
            values.put(UserTable.KEY_USERNAME, user.getUserName());
            values.put(UserTable.KEY_PASSWORD, user.getPassword());
            values.put(UserTable.KEY_TYPE_USER, user.getTypeUser());
            db.insert(UserTable.TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + UserTable.TABLE_NAME;
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
            values.put(ProductTable.KEY_NAME,product.getName());
            values.put(ProductTable.KEY_IMAGE,product.getImageByteArr());
            values.put(ProductTable.KEY_UNIT, product.getUnit());
            values.put(ProductTable.KEY_PRICE,product.getPrice());
            values.put(ProductTable.KEY_SALE, product.getSale());
            values.put(ProductTable.KEY_AVAILABLE_QUANTITY, product.getAvailableQuantity());
            values.put(ProductTable.KEY_SPECIES, product.getSpecies());
            values.put(ProductTable.KEY_BARCODE, product.getBarcode());
            db.insert(ProductTable.TABLE_NAME,null,values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public int updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.KEY_USERNAME,user.getUserName());
        values.put(UserTable.KEY_PHONE_NUMBER,user.getPhoneNumber());
        values.put(UserTable.KEY_GENDER,user.getGender());
        values.put(UserTable.KEY_PASSWORD,user.getPassword());
        return db.update(UserTable.TABLE_NAME,
                values,
                UserTable.KEY_ID+"=?",
                new String[]{String.valueOf(user.getId())});
    }
    public int deleteUser(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(UserTable.TABLE_NAME,
                UserTable.KEY_ID+"=?",
                new String[]{String.valueOf(id)});
    }
    public int updateProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductTable.KEY_NAME,product.getName());
        values.put(ProductTable.KEY_IMAGE,product.getImageByteArr());
        values.put(ProductTable.KEY_UNIT,product.getUnit());
        values.put(ProductTable.KEY_PRICE,product.getPrice());
        values.put(ProductTable.KEY_SALE, product.getSale());
        values.put(ProductTable.KEY_AVAILABLE_QUANTITY, product.getAvailableQuantity());
        values.put(ProductTable.KEY_SPECIES, product.getSpecies());
        values.put(ProductTable.KEY_BARCODE, product.getBarcode());
        return db.update(ProductTable.TABLE_NAME,
                values,
                ProductTable.KEY_ID+"=?",
                new String[]{String.valueOf(product.getId())});
    }
    public int deleteProduct(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ProductTable.TABLE_NAME,
                ProductTable.KEY_ID+"=?",
                new String[]{String.valueOf(id)});
    }
    public void addTable(Table table) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(FurnitureTable.KEY_NAME,table.getName());
            db.insert(FurnitureTable.TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<Table> getTables(){
        ArrayList<Table> tables = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + FurnitureTable.TABLE_NAME;
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
        values.put(FurnitureTable.KEY_NAME,table.getName());
        return db.update(FurnitureTable.TABLE_NAME,
                values,
                FurnitureTable.KEY_ID+"=?",
                new String[]{String.valueOf(table.getId())});
    }
    public int deleteTable(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FurnitureTable.TABLE_NAME,
                FurnitureTable.KEY_ID+"=?",
                new String[]{String.valueOf(id)});
    }
    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + ProductTable.TABLE_NAME;
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
            values.put(InvoiceTable.KEY_ID_USER,invoice.getIdAccount());
            values.put(InvoiceTable.KEY_ID_PRODUCT,invoice.getIdProduct());
            values.put(InvoiceTable.KEY_ID_TABLE, invoice.getIdTable());
            values.put(InvoiceTable.KEY_TOTAL_MONEY, invoice.getTotalMoney());
            values.put(InvoiceTable.KEY_INTO_MONEY, invoice.getInToMoney());
            values.put(InvoiceTable.KEY_DATE, "20/10/2222");
            values.put(InvoiceTable.KEY_TYPE_PAY, invoice.getTypePay());
            values.put(InvoiceTable.KEY_PAYED, invoice.getIsPay());
            db.insert(InvoiceTable.TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public int updateInvoice(Invoice invoice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InvoiceTable.KEY_ID, invoice.getId());
        values.put(InvoiceTable.KEY_ID_USER,invoice.getIdAccount());
        values.put(InvoiceTable.KEY_ID_PRODUCT,invoice.getIdProduct());
        values.put(InvoiceTable.KEY_ID_TABLE, invoice.getIdTable());
        values.put(InvoiceTable.KEY_TOTAL_MONEY, invoice.getTotalMoney());
        values.put(InvoiceTable.KEY_INTO_MONEY, invoice.getInToMoney());
        values.put(InvoiceTable.KEY_DATE, invoice.getDateBuy());
        values.put(InvoiceTable.KEY_TYPE_PAY, invoice.getTypePay());
        values.put(InvoiceTable.KEY_PAYED, invoice.getIsPay());
        return db.update(InvoiceTable.TABLE_NAME,
                values,
                InvoiceTable.KEY_ID+"=?",
                new String[]{String.valueOf(invoice.getId())});
    }
    public int deleteInvoice(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(InvoiceTable.TABLE_NAME,
                InvoiceTable.KEY_ID+"=?",
                new String[]{String.valueOf(id)});
    }
    public ArrayList<Invoice> getInvoices(){
        ArrayList<Invoice> invoices = new ArrayList<>();
        String selectQuery = "";
//        if(typePay.equals(Constance.TYPE_STORE)){
            selectQuery = "SELECT * FROM invoice"; //iv INNER JOIN users u ON iv.id_account=u.id"
                    //+" INNER JOIN tables t ON iv.id_table=t.id"
                    //+" INNER JOIN products p ON iv.id_product=p.id";
//        }else if(typePay.equals(Constance.TYPE_PAY)){
//            selectQuery = "SELECT * FROM invoices iv INNER JOIN users u ON iv.id_account=u.id"
//                    +" INNER JOIN products p ON iv.id_product=p.id";
//        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Invoice invoice = new Invoice();
                invoice.setId(cursor.getLong(0));
                invoice.setIdAccount(cursor.getLong(1));
                invoice.setIdProduct(cursor.getLong(2));
                invoice.setIdTable(cursor.getLong(3));
                invoice.setTotalMoney(cursor.getFloat(4));
                invoice.setInToMoney(cursor.getFloat(5));
                invoice.setDateBuy(cursor.getString(6));
                invoice.setTypePay(cursor.getString(7));
                invoice.setIsPay(cursor.getInt(8));
                invoices.add(invoice);
            } while(cursor.moveToNext());
        }
        return invoices;
    }
    public void addDetailInvoice(InvoiceDetail invoiceDetail) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(InvoiceDetailTable.KEY_ID_INVOICE,invoiceDetail.getIdInvoice());
            values.put(InvoiceDetailTable.KEY_ID_PRODUCT, invoiceDetail.getIdProduct());
            values.put(InvoiceDetailTable.KEY_COUNT,invoiceDetail.getCount());
            db.insert(InvoiceDetailTable.TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<InvoiceDetail> getDetailInvoices(){
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();
        String selectQuery;
        selectQuery = "SELECT * FROM detail_invoice INNER JOIN invoice ON detail_invoice.id_invoice = invoice.id";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                InvoiceDetail invoiceDetail = new InvoiceDetail(cursor.getLong(0), cursor.getLong(1));
                invoiceDetail.setCount(cursor.getInt(2));
               // Log.d("KMFG",cursor.getColumnIndexOrThrow("id_table")+" ===");
               // Log.d("KMFG", cursor.getInt(11)+ " ===");
                invoiceDetail.setIdTable(cursor.getLong(6));
                invoiceDetail.setTypePay(cursor.getString(10));
                invoiceDetail.setIsPay(cursor.getInt(11));
                invoiceDetails.add(invoiceDetail);
            } while(cursor.moveToNext());
        }
        return invoiceDetails;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
