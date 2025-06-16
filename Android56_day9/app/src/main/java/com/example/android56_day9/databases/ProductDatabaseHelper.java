package com.example.android56_day9.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android56_day9.interfaces.db_product_listener.InsertProductListener;
import com.example.android56_day9.models.Product;

import java.util.ArrayList;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "product.sql";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_THUMBNAIL = "thumbnail";

    public ProductDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE \"" + TABLE_NAME + "\" (\n" +
                "\t\"" + COLUMN_ID + "\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"" + COLUMN_TITLE + "\" TEXT NOT NULL,\n" +
                "\t\"" + COLUMN_DESCRIPTION + "\" TEXT,\n" +
                "\t\"" + COLUMN_CATEGORY + "\" INTEGER NOT NULL,\n" +
                "\t\"" + COLUMN_PRICE + "\" INTEGER NOT NULL,\n" +
                "\t\"" + COLUMN_THUMBNAIL + "\" TEXT\n" +
                ")";

        db.execSQL(sql);
    }

    public void insertProduct(Product product, InsertProductListener callback) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, product.getId());
        values.put(COLUMN_TITLE, product.getTitle());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        values.put(COLUMN_CATEGORY, product.getCategory());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_THUMBNAIL, product.getThumbnail());
        long result = db.insert(TABLE_NAME, null, values);
        if (result != -1) {
            callback.onInsertProductFail("Failed to insert product");

        } else {
            callback.onInsertProductSuccess();
        }
        db.close();
    }

    public void updateProductTitle(int id, String title) {
        SQLiteDatabase db = getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    public void removeProduct(int id) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public ArrayList<Product> getAllProductFromLocal() {
        ArrayList<Product> products = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
                int categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
                int priceIndex = cursor.getColumnIndex(COLUMN_PRICE);
                int thumbnailIndex = cursor.getColumnIndex(COLUMN_THUMBNAIL);
                int id = cursor.getInt(idIndex);
                String title = cursor.getString(titleIndex);
                String description = cursor.getString(descriptionIndex);
                String category = cursor.getString(categoryIndex);
                double price = cursor.getDouble(priceIndex);
                String thumbnail = cursor.getString(thumbnailIndex);

                Product product = new Product();
                product.setId(id);
                product.setTitle(title);
                product.setDescription(description);
                product.setCategory(category);
                product.setPrice(price);
                product.setThumbnail(thumbnail);
                products.add(product);
                cursor.moveToNext();

            } while (cursor.moveToNext());
        }

        db.close();
        return products;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
