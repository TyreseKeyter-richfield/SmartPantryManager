package com.TyreseKeyter.smartpantrymanager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.TyreseKeyter.smartpantrymanager.DatabaseHelper;
import com.TyreseKeyter.smartpantrymanager.Recipe;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity{
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest_recipes);
        databaseHelper = new DatabaseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSuggested);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView emptyText = findViewById(R.id.textEmptyState);
        List<Recipe> suggested=databaseHelper.getsuggestedrecipes();
        if (suggested.isEmpty()){
            emptyText.setVisibility(TextView.VISIBLE);
            emptyText.setText("With the ingredients you currently have, you cannot make any recipes");
            recyclerView.setVisibility(RecyclerView.GONE);
        } else {
            emptyText.setVisibility(TextView.GONE);
            recyclerView.setVisibility(RecyclerView.VISIBLE);
            RecipeAdapter adapter = new RecipeAdapter(suggested, this);
            recyclerView.setAdapter(adapter);
        }
    }
}
