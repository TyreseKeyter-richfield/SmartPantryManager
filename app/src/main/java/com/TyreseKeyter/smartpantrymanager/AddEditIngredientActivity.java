package com.TyreseKeyter.smartpantrymanager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.TyreseKeyter.smartpantrymanager.DatabaseHelper;
import com.TyreseKeyter.smartpantrymanager.Ingredient;
import java.util.List;
public class AddEditIngredientActivity extends AppCompatActivity{
    private EditText inputName, inputQuantity, inputUnit, inputExpiry;
    private DatabaseHelper databaseHelper;
    private int editingId = -1;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_ingredient);
        databaseHelper=new DatabaseHelper(this);
        inputName =findViewById(R.id.editName);
        inputQuantity=findViewById(R.id.editQuantity);
        inputUnit = findViewById(R.id.editUnit);
        inputExpiry = findViewById(R.id.Expiry);
        Button saveButton = findViewById(R.id.btnSave);
        editingId=getIntent().getIntExtra("ingredient_id", -1);
        if (editingId !=-1){
            prefillFieldsForEditing();
            }
            saveButton.setOnClickListener(v -> saveIngredient());
        }
        private void prefillFieldsForEditing(){
        List<Ingredient> all=databaseHelper.getallIngredients();
        for(Ingredient item:all){
            if (item.getId()== editingId){
                inputName.setText(item.getName());
                inputQuantity.setText(String.valueOf(item.getQuantity()));
                inputUnit.setText(item.getUnit());
                inputExpiry.setText(item.getexpirydate());
                break;
            }
            }
        }
        private void saveIngredient(){
            String name = inputName.getText().toString().trim();
            String quantityText = inputQuantity.getText().toString().trim();
            String unit = inputUnit.getText().toString().trim();
            String expiry = inputExpiry.getText().toString().trim();
            if(name.isEmpty()){
                inputName.setError("Please insert an ingredient name.");
                return;
            }
            if (quantityText.isEmpty()){
                inputQuantity.setError("Please insert a quantity.");
                return;
            }
            double quantity;
            try {
                quantity = Double.parseDouble(quantityText);
            } catch (NumberFormatException e){
                inputQuantity.setError("Please insert a number.");
                return;
            }
            if (quantity <=0){
                inputQuantity.setError("Please insert a number higher than zero (0).");
                return;
            }
            Ingredient ingredient;
            if (editingId == -1){
                ingredient = new Ingredient(name, quantity, unit, expiry);
                databaseHelper.addingredient(ingredient);
                Toast.makeText(this, "New Ingredient Added", Toast.LENGTH_SHORT).show();
            } else {
                ingredient = new Ingredient(editingId, name, quantity, unit, expiry);
                databaseHelper.updateIngredient(ingredient);
                Toast.makeText(this, "New Ingredient Added", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
