package com.TyreseKeyter.smartpantrymanager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.TyreseKeyter.smartpantrymanager.Ingredient;
import com.TyreseKeyter.smartpantrymanager.Recipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smartpantrymanager.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PANTRY = "pantryitems";
    private static final String TABLE_RECIPES = "recipes";
    private static final String TABLE_RECIPE_INGREDIENTS = "recipeingredients";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PANTRY + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "quantity REAL NOT NULL, " +
                "unit TEXT, " +
                "expirydate TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_RECIPES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "steps TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "recipe_id INTEGER NOT NULL, " +
                "ingredient_name TEXT NOT NULL, " +
                "FOREIGN KEY(recipe_id) REFERENCES " + TABLE_RECIPES + "(id))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        onCreate(db);
    }
    public long addingredient(Ingredient ingredient){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name", ingredient.getName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit",ingredient.getUnit());
        values.put("expirydate",ingredient.getexpirydate());
        long newId=db.insert(TABLE_PANTRY,null,values);
        db.close();
        return newId;
    }
    public List<Ingredient> getallIngredients(){
        List<Ingredient> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM " + TABLE_PANTRY + " ORDER BY name ASC", null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            double qty=cursor.getDouble(cursor.getColumnIndexOrThrow("quantity"));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"));
            String expiry =cursor.getString(cursor.getColumnIndexOrThrow("expirydate"));
            list.add(new Ingredient(id, name, qty, unit, expiry));
        }
        cursor.close();
        db.close();
        return list;
    }
    public int updateIngredient(Ingredient ingredient){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", ingredient.getName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit", ingredient.getUnit());
        values.put("expirydate", ingredient.getexpirydate());
        int rowsAffected = db.update(TABLE_PANTRY, values, "id = ?",
                new String[]{String.valueOf(ingredient.getId())});
        db.close();
        return rowsAffected;
    }
    public void deleteIngredient (int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PANTRY, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery ("SELECT * FROM " + TABLE_RECIPES, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String steps = cursor.getString(cursor.getColumnIndexOrThrow("steps"));
            List<String> ingredients = new ArrayList<>();
            Cursor ingCursor = db.rawQuery(
                    "SELECT ingredientname FROM " + TABLE_RECIPE_INGREDIENTS + "WHERE recipeid = ?",
                    new String[]{String.valueOf(id)});
            while (ingCursor.moveToNext()){
                ingredients.add(ingCursor.getString(0));
            }
            ingCursor.close();
            recipes.add(new Recipe(id, name, steps, ingredients));
        }
        cursor.close();
        db.close();
        return recipes;
    }
    private
}