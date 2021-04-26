package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.quanlyquancafeapp.model.Customer;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.utils.Constance;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cafeManage";
    public static DatabaseHelper context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DatabaseHelper.context = this;
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
                UserTable.KEY_PROFILE + " TEXT," +
                UserTable.KEY_TYPE_USER + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_USER_TIME_TABLE = "CREATE TABLE " + UserTimeTable.TABLE_NAME + " (" +
                UserTimeTable.KEY_ID_USER_TIME+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserTimeTable.KEY_ID_USER + " INTEGER," +
                UserTimeTable.KEY_TIME_START + " TEXT," +
                UserTimeTable.KEY_TIME_END + " TEXT," +
                " FOREIGN KEY ("+ UserTimeTable.KEY_ID_USER+") REFERENCES "+UserTable.TABLE_NAME+"("+UserTable.KEY_ID+"));";
        db.execSQL(CREATE_USER_TIME_TABLE);

        String CREATE_USER_WORKING_TABLE = "CREATE TABLE " + UserWorkingTable.TABLE_NAME + " (" +
                UserWorkingTable.KEY_ID_USER + " INTEGER," +
                UserWorkingTable.KEY_DATE + " TEXT," +
                UserWorkingTable.KEY_TIME_START + " TEXT," +
                UserWorkingTable.KEY_TIME_END + " TEXT," +
                " FOREIGN KEY ("+ UserWorkingTable.KEY_ID_USER+") REFERENCES "+UserTable.TABLE_NAME+"("+UserTable.KEY_ID+"));";
        db.execSQL(CREATE_USER_WORKING_TABLE);

        String CREATE_TABLES_FURNITURE = "CREATE TABLE " + FurnitureTable.TABLE_NAME + " (" +
                FurnitureTable.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FurnitureTable.KEY_COUNT_PEOPLE + " INTEGER," +
                FurnitureTable.KEY_COUNT_CURRENT_PEOPLE + " INTEGER," +
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
                + InvoiceTable.KEY_TIME + " TEXT,"
                + InvoiceTable.KEY_TYPE_PAY + " TEXT,"
                + InvoiceTable.KEY_PAYED + " INTEGER,"
                + " FOREIGN KEY ("+InvoiceTable.KEY_ID_USER+") REFERENCES "+UserTable.TABLE_NAME+"("+UserTable.KEY_ID+"),"
                + " FOREIGN KEY ("+InvoiceTable.KEY_ID_TABLE+") REFERENCES "+FurnitureTable.TABLE_NAME+"("+FurnitureTable.KEY_ID+"));";
        db.execSQL(CREATE_TABLE_INVOICE);

        String CREATE_TABLES_CUSTOMER = "CREATE TABLE " + CustomerTable.TABLE_NAME + " (" +
                CustomerTable.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CustomerTable.KEY_COUNT_PERSON +" INTEGER," +
                CustomerTable.KEY_DONE + " INTEGER," +
                CustomerTable.KEY_NAME + " TEXT)";
        db.execSQL(CREATE_TABLES_CUSTOMER);

        String CREATE_TABLES_DETAIL_INVOICE = "CREATE TABLE " + InvoiceDetailTable.TABLE_NAME + " (" +
                InvoiceDetailTable.KEY_ID_INVOICE + " INTEGER," +
                InvoiceDetailTable.KEY_ID_PRODUCT + " INTEGER,"+
                InvoiceDetailTable.KEY_ID_TABLE + " INTEGER,"+
                InvoiceDetailTable.KEY_COUNT + " INTEGER,"+
                InvoiceDetailTable.KEY_ID_CUSTOMER + " INTEGER," +
                InvoiceDetailTable.KEY_SALE + " TEXT," +
                InvoiceDetailTable.KEY_DESCRIPTION+ " TEXT," +
                InvoiceDetailTable.KEY_NAME_PRODUCT+ " TEXT," +
                " FOREIGN KEY ("+ InvoiceDetailTable.KEY_ID_INVOICE+") REFERENCES "+InvoiceTable.TABLE_NAME+"("+InvoiceTable.KEY_ID+"),"
                + " FOREIGN KEY ("+ InvoiceDetailTable.KEY_ID_TABLE+") REFERENCES "+FurnitureTable.TABLE_NAME+"("+FurnitureTable.KEY_ID+"),"
                + " FOREIGN KEY ("+ InvoiceDetailTable.KEY_ID_CUSTOMER+") REFERENCES "+CustomerTable.TABLE_NAME+"("+CustomerTable.KEY_ID+"),"
                + " FOREIGN KEY ("+ InvoiceDetailTable.KEY_ID_PRODUCT+") REFERENCES "+ProductTable.TABLE_NAME+"("+ProductTable.KEY_ID+"));";
        db.execSQL(CREATE_TABLES_DETAIL_INVOICE);
    }

    public void addCustomer(Customer customer) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CustomerTable.KEY_NAME,customer.getName());
            values.put(CustomerTable.KEY_COUNT_PERSON, customer.getCount());
            values.put(CustomerTable.KEY_DONE, customer.getDone());

            db.insert(CustomerTable.TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<Customer> getCustomers(){
        ArrayList<Customer> customers = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + CustomerTable.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        //Log.d("KMFG77",cursor.getColumnIndexOrThrow("name_person")+" ===");
        if(cursor.moveToFirst()){
            do{
                Customer customer = new Customer();
                customer.setId(cursor.getLong(0));
                customer.setName(cursor.getString(3));
                customer.setCount(cursor.getInt(1));
                //customer.setDone(cursor.getInt(3));
                customers.add(customer);
            } while(cursor.moveToNext());
        }
        return customers;
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
            values.put(UserTable.KEY_PROFILE, user.getFilePath());
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
                //Log.d("KMFG58",cursor.getColumnIndexOrThrow("type_user")+" ===ABC");
                User user = new User();
                user.setId(cursor.getLong(0));
                user.setNameStore(cursor.getString(1));
                user.setAddress(cursor.getString(2));
                user.setPhoneNumber(cursor.getString(3));
                user.setGender(cursor.getString(4));
                user.setUserName(cursor.getString(5));
                user.setPassword(cursor.getString(6));
                user.setTypeUser(cursor.getString(8));
                user.setFilePath(cursor.getString(7));
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
        values.put(UserTable.KEY_PROFILE, user.getFilePath());
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
        Log.d("KMFG123", product.getId()+" ========");
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
    public void addTable(Table table) throws Exception {
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(FurnitureTable.KEY_NAME,table.getName());
            values.put(FurnitureTable.KEY_COUNT_PEOPLE,table.getCountPeople());
            values.put(FurnitureTable.KEY_COUNT_CURRENT_PEOPLE, table.getCountCurrentPeople());
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
                //Log.d("KMFG","TYPE="+cursor.getColumnIndexOrThrow("count_people"));
                Table table = new Table();
                table.setId(cursor.getLong(0));
                table.setName(cursor.getString(3));
                table.setCountPeople(cursor.getInt(1));
                table.setCountCurrentPeople(cursor.getInt(2));
                tables.add(table);
            } while(cursor.moveToNext());
        }
        return tables;
    }
    public int updateTable(Table table){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FurnitureTable.KEY_NAME,table.getName());
        values.put(FurnitureTable.KEY_COUNT_PEOPLE,table.getCountPeople());
        values.put(FurnitureTable.KEY_COUNT_CURRENT_PEOPLE, table.getCountCurrentPeople());
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
            values.put(InvoiceTable.KEY_ID_TABLE, invoice.getIdTable());
            values.put(InvoiceTable.KEY_TOTAL_MONEY, invoice.getTotalMoney());
            values.put(InvoiceTable.KEY_INTO_MONEY, invoice.getInToMoney());
            values.put(InvoiceTable.KEY_DATE, invoice.getDateBuy());
            values.put(InvoiceTable.KEY_TIME, invoice.getTime());
            values.put(InvoiceTable.KEY_TYPE_PAY, invoice.getTypePay());
            values.put(InvoiceTable.KEY_PAYED, 0);

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
        //values.put(InvoiceTable.KEY_ID_USER,invoice.getIdAccount());
        values.put(InvoiceTable.KEY_ID_TABLE, invoice.getIdTable());
        values.put(InvoiceTable.KEY_TOTAL_MONEY, invoice.getTotalMoney());
        //values.put(InvoiceTable.KEY_INTO_MONEY, invoice.getInToMoney());
        values.put(InvoiceTable.KEY_DATE, invoice.getDateBuy());
        values.put(InvoiceTable.KEY_TIME, invoice.getTime());
        values.put(InvoiceTable.KEY_TYPE_PAY, invoice.getTypePay());
        values.put(InvoiceTable.KEY_PAYED, invoice.getIsPay());
        return db.update(InvoiceTable.TABLE_NAME,
                values,
                InvoiceTable.KEY_ID+"=?",
                new String[]{String.valueOf(invoice.getId())});
    }
    public ArrayList<Invoice> getInvoices(){
        ArrayList<Invoice> invoices = new ArrayList<>();
        String selectQuery = "SELECT * FROM invoice";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        //Log.d("KMFG99",cursor.getColumnIndexOrThrow("payed")+" ===");
        if(cursor.moveToFirst()){
            do{
                Invoice invoice = new Invoice();
                //Log.d("KMFG", cursor.getString(6)+ " IDD===");
                invoice.setId(cursor.getLong(0));
                invoice.setIdAccount(cursor.getLong(1));
                invoice.setIdTable(cursor.getLong(2));
                invoice.setTotalMoney(cursor.getFloat(3));
                invoice.setInToMoney(cursor.getFloat(4));
                invoice.setDateBuy(cursor.getString(6));
                invoice.setTime(cursor.getString(7));
                invoice.setTypePay(cursor.getString(8));
                invoice.setIsPay(cursor.getInt(9));
                invoices.add(invoice);
            } while(cursor.moveToNext());
        }
        return invoices;
    }
    public ArrayList<Invoice> getInvoicesSort(){
        ArrayList<Invoice> invoices = new ArrayList<>();
        String selectQuery = "SELECT * FROM invoice";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Invoice invoice = new Invoice();
                //Log.d("KMFG",cursor.getColumnIndexOrThrow("time")+" ===");
                //Log.d("KMFG", cursor.getString(6)+ " IDD===");
                invoice.setId(cursor.getLong(0));
                invoice.setIdAccount(cursor.getLong(1));
                invoice.setIdTable(cursor.getLong(2));
                invoice.setTotalMoney(cursor.getFloat(3));
                invoice.setInToMoney(cursor.getFloat(4));
                invoice.setDateBuy(cursor.getString(6));
                invoice.setTime(cursor.getString(7));
                invoice.setTypePay(cursor.getString(8));
                invoice.setIsPay(cursor.getInt(9));
                invoices.add(0,invoice);
            } while(cursor.moveToNext());
        }
        return invoices;
    }

    public static Long idTable;
    public void addDetailInvoice(InvoiceDetail invoiceDetail) throws Exception{
        SQLiteDatabase db = null;
        int size = getCustomers().size();
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(InvoiceDetailTable.KEY_ID_INVOICE,invoiceDetail.getIdInvoice());
            values.put(InvoiceDetailTable.KEY_ID_PRODUCT, invoiceDetail.getIdProduct());
            values.put(InvoiceDetailTable.KEY_COUNT,invoiceDetail.getCount());
            values.put(InvoiceDetailTable.KEY_SALE, invoiceDetail.getSale());
            values.put(InvoiceDetailTable.KEY_DESCRIPTION, invoiceDetail.getDescription());
            values.put(InvoiceDetailTable.KEY_NAME_PRODUCT, invoiceDetail.getNameProduct());
            if(Constance.TYPE_PAY.equals("SHELL")){
                values.put(InvoiceDetailTable.KEY_ID_CUSTOMER,getCustomers().get(size-1).getId());
            }
            values.put(InvoiceDetailTable.KEY_ID_TABLE, idTable);
            db.insert(InvoiceDetailTable.TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }

    public void addDetailInvoiceById(InvoiceDetail invoiceDetail) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(InvoiceDetailTable.KEY_ID_INVOICE,invoiceDetail.getIdInvoice());
            values.put(InvoiceDetailTable.KEY_ID_PRODUCT, invoiceDetail.getIdProduct());
            values.put(InvoiceDetailTable.KEY_COUNT,invoiceDetail.getCount());
            values.put(InvoiceDetailTable.KEY_SALE, invoiceDetail.getSale());
            values.put(InvoiceDetailTable.KEY_DESCRIPTION, invoiceDetail.getDescription());
            values.put(InvoiceDetailTable.KEY_ID_CUSTOMER,invoiceDetail.getCustomer().getId());
            values.put(InvoiceDetailTable.KEY_NAME_PRODUCT, invoiceDetail.getNameProduct());
            values.put(InvoiceDetailTable.KEY_ID_TABLE, invoiceDetail.getIdTable());

            db.insert(InvoiceDetailTable.TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }

    public ArrayList<InvoiceDetail> getDetailInvoices(){
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();
        String selectQuery = "SELECT * FROM detail_invoice INNER JOIN invoice ON detail_invoice.id_invoice = invoice.id_invoice";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //Log.d("KMFG99","TYPE="+cursor.getColumnIndexOrThrow("id_customer"));

        if(cursor.moveToFirst()){
            do{
                InvoiceDetail invoiceDetail = new InvoiceDetail(cursor.getLong(0), cursor.getLong(1));
                invoiceDetail.setCount(cursor.getInt(3));
                //Log.d("KMFG", cursor.getInt(3)+ " IDD===");
                invoiceDetail.setSale(cursor.getString(5));
                invoiceDetail.setId(cursor.getLong(8));
                //invoiceDetail.setIdTable(cursor.getLong(8));
                invoiceDetail.setTypePay(cursor.getString(16));
                invoiceDetail.setIsPay(cursor.getInt(17));
                invoiceDetail.getCustomer().setId(cursor.getLong(4));
                invoiceDetails.add(invoiceDetail);
            } while(cursor.moveToNext());
        }
        return invoiceDetails;
    }

    public ArrayList<InvoiceDetail> getDetailInvoicesNotTableById(Long id){
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();
        String selectQuery = "SELECT * FROM detail_invoice INNER JOIN invoice ON detail_invoice.id_invoice = invoice.id_invoice "
                + "INNER JOIN product ON product.id = detail_invoice.id_product " +
                "WHERE invoice.id_invoice=?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        //Log.d("KMFG00",cursor.getColumnIndexOrThrow("name_product_invoice")+" ===ABC");
        if(cursor.moveToFirst()){
            do{
                InvoiceDetail invoiceDetail = new InvoiceDetail(cursor.getLong(0), cursor.getLong(1));
                invoiceDetail.setCount(cursor.getInt(3));
                //Log.d("KMFG55", cursor.getString(21)+ " IDD===");

                invoiceDetail.setSale(cursor.getString(5));
                invoiceDetail.setId(cursor.getLong(8));
                invoiceDetail.setNameProduct(cursor.getString(7));
                invoiceDetail.setIdProduct(cursor.getLong(10));
                invoiceDetail.setDateBuy(cursor.getString(14));
//                invoiceDetail.setTypePay(cursor.getString(11));
                invoiceDetail.setIsPay(cursor.getInt(17));

                Product product = new Product();
                product.setName(cursor.getString(19));
                product.setPrice(cursor.getFloat(22));
                product.setSale(cursor.getString(23));
                invoiceDetail.setProduct(product);

                invoiceDetails.add(invoiceDetail);
            } while(cursor.moveToNext());
        }
        return invoiceDetails;
    }

    public ArrayList<InvoiceDetail> getDetailInvoicesCustomer(){
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();
        String selectQuery = "SELECT * FROM detail_invoice INNER JOIN customer ON detail_invoice.id_customer = customer.id_customer "
                + "INNER JOIN furniture ON furniture.id_table = detail_invoice.id_table "
                + "INNER JOIN invoice ON detail_invoice.id_invoice = invoice.id_invoice "
                + "INNER JOIN product ON product.id = detail_invoice.id_product";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Log.d("KMFG55",cursor.getColumnIndexOrThrow("id_customer")+" =COLUM");
        if(cursor.moveToFirst()){
            do{
                InvoiceDetail invoiceDetail = new InvoiceDetail(cursor.getLong(0), cursor.getLong(1));
                invoiceDetail.setId(cursor.getLong(16));
                invoiceDetail.setCount(cursor.getInt(3));
                invoiceDetail.setTime(cursor.getString(23));
                invoiceDetail.setTypePay(cursor.getString(24));
                invoiceDetail.setIsPay(cursor.getInt(25));
                invoiceDetail.setDescription(cursor.getString(6));
//                Log.d("KMFG", cursor.getLong(11)+ " =000");

                Customer customer = new Customer();
                customer.setId(cursor.getLong(8));
                customer.setCount(cursor.getInt(9));
                customer.setDone(cursor.getInt(10));
                customer.setName(cursor.getString(11));
                invoiceDetail.setCustomer(customer);

                Product product = new Product();
                product.setName(cursor.getString(27));
                product.setImageByteArr(cursor.getBlob(28));
                invoiceDetail.setProduct(product);

                invoiceDetail.setIdTable(cursor.getLong(19));
                invoiceDetails.add(invoiceDetail);
            } while(cursor.moveToNext());
        }
        return invoiceDetails;
    }

    public ArrayList<InvoiceDetail> getDetailInvoicesRevenueDetail(){
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();
        String selectQuery = "SELECT * FROM detail_invoice "
                + "INNER JOIN invoice ON detail_invoice.id_invoice = invoice.id_invoice "
                + "INNER JOIN product ON product.id = detail_invoice.id_product";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d("KMFG55",cursor.getColumnIndexOrThrow("name_product")+" =COLUM");
        if(cursor.moveToFirst()){
            do{
                InvoiceDetail invoiceDetail = new InvoiceDetail(cursor.getLong(0), cursor.getLong(1));
                invoiceDetail.setCount(cursor.getInt(3));
                invoiceDetail.setSale(cursor.getString(23));
                invoiceDetail.setDateBuy(cursor.getString(14));
                invoiceDetail.setTime(cursor.getString(15));
                invoiceDetail.setIsPay(cursor.getInt(17));
//                invoiceDetail.setId(cursor.getLong(11));
               // Log.d("KMFG123", cursor.getLong(11)+ " =000");

                Product product = new Product();
                product.setName(cursor.getString(19));
                product.setImageByteArr(cursor.getBlob(20));
                product.setPrice(cursor.getFloat(22));
                invoiceDetail.setProduct(product);

                invoiceDetails.add(invoiceDetail);
            } while(cursor.moveToNext());
        }
        return invoiceDetails;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
