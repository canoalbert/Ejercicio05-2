package alberto.cano.ejercicio05_2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alberto.cano.ejercicio05_2.modelos.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductVH>{
    private List<Product> objects;
    private  int resources;
    private Context context;





    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(context).inflate(resources, null);
        productView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new ProductVH(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {
        Product product = objects.get(position);
        holder.lbName.setText(product.getName());
        holder.lbQuantity.setText(String.valueOf(product.getQuantity()));

        holder.btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                confirmDekete(product).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ProductVH extends RecyclerView.ViewHolder{

        public ProductVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
