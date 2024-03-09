package com.example.mylib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + Util.BOOKS_TABLE_NAME + " ("
                + Util.BOOK_ID + " INTEGER PRIMARY KEY, "
                + Util.BOOK_NAME + " TEXT, "
                + Util.BOOK_AUTHOR_NAME + " TEXT, "
                + Util.BOOK_DATE + " TEXT, "
                + Util.BOOK_PAGES + " INTEGER, "
                + Util.BOOK_DESCRIPTION + " TEXT, "
                + Util.BOOK_GENRE + " TEXT, "
                + Util.BOOK_STATUS + " TEXT, "
                + Util.BOOK_PHOTO + " INTEGER" + " );";

        sqLiteDatabase.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.BOOKS_TABLE_NAME + ";");
        onCreate(sqLiteDatabase);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.BOOKS_TABLE_NAME, null, null);
    }

    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues bookContentValues = new ContentValues();
        bookContentValues.put(Util.BOOK_NAME, book.getBook_name());
        bookContentValues.put(Util.BOOK_AUTHOR_NAME, book.getAuthor_name());
        bookContentValues.put(Util.BOOK_DATE, book.getDate());
        bookContentValues.put(Util.BOOK_PAGES, book.getPages());
        bookContentValues.put(Util.BOOK_DESCRIPTION, book.getDescription());
        bookContentValues.put(Util.BOOK_GENRE, book.getGenre());
        bookContentValues.put(Util.BOOK_STATUS, book.getStatus());
        bookContentValues.put(Util.BOOK_PHOTO, book.getPhoto());
        db.insert(Util.BOOKS_TABLE_NAME, null, bookContentValues);
        //db.close();
    }

    public Book getBook(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.BOOKS_TABLE_NAME, new String[]{Util.BOOK_ID, Util.BOOK_NAME, Util.BOOK_AUTHOR_NAME, Util.BOOK_DATE, Util.BOOK_PAGES, Util.BOOK_DESCRIPTION, Util.BOOK_GENRE, Util.BOOK_STATUS, Util.BOOK_PHOTO},
                Util.BOOK_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            Book book = new Book(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5), cursor.getString(6), cursor.getString(7), Integer.parseInt(cursor.getString(8)));
            cursor.close();
            return book;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public ArrayList<Book> getAllBooks() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Book> booksList = new ArrayList<>();
        String selectAll = "Select * from " + Util.BOOKS_TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {

            do {
                Book book = new Book();
                book.setBook_name(cursor.getString(1));
                book.setAuthor_name(cursor.getString(2));
                book.setDate(cursor.getString(3));
                book.setPages(Integer.parseInt(cursor.getString(4)));
                book.setDescription(cursor.getString(5));
                book.setGenre(cursor.getString(6));
                book.setStatus(cursor.getString(7));
                book.setPages(Integer.parseInt(cursor.getString(8)));
                booksList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("books", booksList.toString());
        return booksList;
    }

    public ArrayList<Book> getBooksByStatus(String status) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Book> booksList = new ArrayList<>();
        String selectByStatus = "SELECT * FROM " + Util.BOOKS_TABLE_NAME + " WHERE " + Util.BOOK_STATUS + " = ?;";
        Cursor cursor = db.rawQuery(selectByStatus, new String[]{status});
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setBook_name(cursor.getString(1));
                book.setAuthor_name(cursor.getString(2));
                book.setDate(cursor.getString(3));
                book.setPages(Integer.parseInt(cursor.getString(4)));
                book.setDescription(cursor.getString(5));
                book.setGenre(cursor.getString(6));
                book.setStatus(cursor.getString(7));
                book.setPages(Integer.parseInt(cursor.getString(8)));
                booksList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("books", booksList.toString());
        return booksList;
    }

    public int countBooksByStatus(String status) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT COUNT(*) FROM " + Util.BOOKS_TABLE_NAME + " WHERE " + Util.BOOK_STATUS + " = ?";
        Cursor cursor = db.rawQuery(countQuery, new String[]{status});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public String findMostPopularGenre() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + Util.BOOK_GENRE + ", COUNT(*) AS count FROM " + Util.BOOKS_TABLE_NAME + " GROUP BY " + Util.BOOK_GENRE + " ORDER BY count DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);
        String mostPopularGenre = null;
        if (cursor.moveToFirst()) {
            mostPopularGenre = cursor.getString(cursor.getColumnIndex(Util.BOOK_GENRE) + 1);
        }
        cursor.close();
        return mostPopularGenre;
    }

    public int updateBook(Book book, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues bookContentValues = new ContentValues();
        bookContentValues.put(Util.BOOK_NAME, book.getBook_name());
        bookContentValues.put(Util.BOOK_AUTHOR_NAME, book.getAuthor_name());
        bookContentValues.put(Util.BOOK_DATE, book.getDate());
        bookContentValues.put(Util.BOOK_PAGES, book.getPages());
        bookContentValues.put(Util.BOOK_DESCRIPTION, book.getDescription());
        bookContentValues.put(Util.BOOK_GENRE, book.getGenre());
        bookContentValues.put(Util.BOOK_STATUS, book.getStatus());
        bookContentValues.put(Util.BOOK_PHOTO, book.getPhoto());

        return db.update(Util.BOOKS_TABLE_NAME, bookContentValues, Util.BOOK_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.BOOKS_TABLE_NAME, Util.BOOK_ID + "=?", new String[]{String.valueOf(id)});
        //db.close();
    }
}