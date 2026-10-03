package com.TyreseKeyter.smartpantrymanager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> recipes;
    private Context context;
    public RecipeAdapter(List <Recipe> recipes, Context context){
        this.recipes= recipes;
        this.context= context;
    }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe,parent, false);
        return new RecipeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.textName.setText(recipe.getName());
        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            intent.putExtra("recipe_id", recipe.getId());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return recipes.size();
    }
    static class RecipeViewHolder extends RecyclerView.ViewHolder{
        TextView textName;
        public RecipeViewHolder(@NonNull View itemView){
            super(itemView);
            textName=itemView.findViewById(R.id.textRecipeName);
        }
    }
}
