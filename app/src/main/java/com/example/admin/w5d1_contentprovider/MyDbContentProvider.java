package com.example.admin.w5d1_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyDbContentProvider extends ContentProvider {

    private DatabaseHelper databaseHelper = null;

    public MyDbContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        String pName = selection;

        return databaseHelper.deletePlayer(pName);

    }

    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case PLAYERS:
                return "vnd.android.cursor.dir/vnd.com.androidcontentproviderdemo.androidcontentprovider.provider.MY_DATABASE";
        }
        return "";

    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseHelper = new DatabaseHelper(context);
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            long id = databaseHelper.addPlayer(values.getAsString("pName"),
                    values.getAsInteger("pAge"),values.getAsString("position"));
            Uri returnUri = ContentUris.withAppendedId(CONTENT_URI, id);
            return returnUri;
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        return databaseHelper.getPlayers(projection, selection, selectionArgs, sortOrder);

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        return databaseHelper.updatePlayer(values.getAsInteger("id"),
                values.getAsString("pName"),values.getAsString("pAge"),
                values.getAsString("position"));
    }


    private static final String PROVIDER_NAME = "/com.example.admin.w5d1_contentprovider";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/MY_DATABASE");
    private static final int PLAYERS = 1;
    private static final UriMatcher uriMatcher = getUriMatcher();

    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "players", PLAYERS);
        return uriMatcher;
    }
}
