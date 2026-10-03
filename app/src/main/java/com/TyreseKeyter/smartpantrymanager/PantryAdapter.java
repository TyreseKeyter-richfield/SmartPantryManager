package com.TyreseKeyter.smartpantrymanager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.TyreseKeyter.smartpantrymanager.DatabaseHelper;
import com.TyreseKeyter.smartpantrymanager.Ingredient;
import java.util.List;
public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder>{
    private List<Ingredient> ingredients;
    private Context context;
    private DatabaseHelper databaseHelper;
    public PantryAdapter(List<Ingredient> ingredients, Context context){
        this.ingredients = ingredients;
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }
    public void updateData(List<Ingredient> newIngredients){
        this.ingredients = newIngredients;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_pantry, parent, false);
        return new PantryViewHolder(view);
    }
    @Override
    public void onBindViewHolder (@NonNull PantryViewHolder holder, int position){
        Ingredient item = ingredients.get(position);
        holder.textName.setText(item.getName());
        holder.textDetails.setText(item.getQuantity() +" "+ item.getUnit());
        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, AddEditIngredientActivity.class);
            intent.putExtra("ingredient_id", item.getId());
            context.startActivity(intent);
        });
        holder.itemView.setOnLongClickListener(v ->{
            databaseHelper.deleteIngredient(item.getId());
            ingredients.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, item.getName() + "deleted" , Toast.LENGTH_SHORT).show();
            return true;
        });
    }
    @Override
    public int getItemCount(){
        return ingredients.size();
    }
    static class PantryViewHolder extends RecyclerView.ViewHolder{
        TextView textName, textDetails;
        public PantryViewHolder (@NonNull View itemView){
            super(itemView);
            textName =itemView.findViewById(R.id.textIngredientName);
            textDetails=itemView.findViewById(R.id.textIngredientDetails);
        }
    }
}
