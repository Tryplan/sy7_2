package com.example.sy7_2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private static UriMatcher uriMatcher;

    private static final String AUTHORITY = "com.example.sy7_2.provider";

    private static final int contacts_DIR = 0;

    private static final int contacts_ITEM = 1;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"contacts",contacts_DIR);
        uriMatcher.addURI(AUTHORITY,"contacts/#",contacts_ITEM);

    }

    private MyDatabaseHelper dhHelper;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
       switch (uriMatcher.match(uri)) {
           case contacts_DIR:
               return "vnd.android.cursor.dir/vnd.com.example.sy7_2.provider.contacts";
           case contacts_ITEM:
               return "vnd.android.cursor.item/vnd.com.example.sy7_2.provider.contacts";
       }
       return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        dhHelper = new MyDatabaseHelper(getContext(),"contacts.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dhHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case contacts_DIR:
                cursor = db.query("contacts",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case contacts_ITEM:
                String id = uri.getPathSegments().get(1);
                cursor = db.query("contacts",projection,"id=?",new String[]{id},null,null,sortOrder);
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
