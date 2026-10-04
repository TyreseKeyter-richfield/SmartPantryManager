package com.TyreseKeyter.smartpantrymanager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.TyreseKeyter.smartpantrymanager.DatabaseHelper;
import com.TyreseKeyter.smartpantrymanager.Recipe;
import java.util.List;
public class RecipeDetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);
        int recipeId = getIntent().getIntExtra("recipe_id", -1);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Recipe>allRecipes = databaseHelper.getAllRecipes();
        TextView textTitle = findViewById(R.id.textRecipeTitle);
        TextView textIngredients = findViewById(R.id.textRecipeIngredients);
        TextView textSteps = findViewById(R.id.textRecipeSteps);
        for (Recipe recipe: allRecipes){
            if (recipe.getId()== recipeId){
                textTitle.setText(recipe.getName());
                textIngredients.setText("Ingredients: " + String.join(",", recipe.getRequiredingredients()));
                textSteps.setText("Method: " + recipe.getSteps());
                break;
            }
        }
    }
}
