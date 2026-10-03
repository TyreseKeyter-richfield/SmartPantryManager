package com.TyreseKeyter.smartpantrymanager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.TyreseKeyter.smartpantrymanager.Ingredient;
import com.TyreseKeyter.smartpantrymanager.DatabaseHelper;
import java.util.List;

public class PantryListActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private PantryAdapter adapter;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);
        databaseHelper=new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewPantry);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button addButton = findViewById(R.id.btnAddIngredient);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantryListActivity.this, AddEditIngredientActivity.class);
                startActivity(intent);
            }
        });
        Button settingButton = findViewById(R.id.btnSettings);
        settingButton.setOnClickListener(v ->{
            startActivity(new Intent(PantryListActivity.this, SettingsActivity.class));
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        loadPantryItems();
    }
    private void loadPantryItems(){
        List<Ingredient> items = databaseHelper.getallIngredients();
        if (adapter == null){
            adapter = new PantryAdapter(items, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateData(items);
        }
        }
    }
}
