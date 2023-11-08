package alberto.cano.ejercicio05_2.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alberto.cano.ejercicio05_2.R;
import alberto.cano.ejercicio05_2.modelos.Product;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductVH>{
    private List<Product> objects;
    private  int resources;
    private Context context;

    public ProductsAdapter(List<Product> objects, int resources, Context context) {
        this.objects = objects;
        this.resources = resources;
        this.context = context;
    }

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
                confirmDelete(product).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {confirmUpdate(product);
            }
        });
    }

    private AlertDialog confirmUpdate(Product product){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.update);
        //No se cierra al hacer click fuera
        builder.setCancelable(false);

        View productViewModel = LayoutInflater.from(context).inflate(R.layout.product_view_model, null);
        EditText txtName = productViewModel.findViewById(R.id.txtNameProductViewModel);
        txtName.setEnabled(false);
        EditText txtQuantity = productViewModel.findViewById(R.id.txtQuantityProductViewModel);
        EditText txtPrice = productViewModel.findViewById(R.id.txtQuantityProductViewModel);
        TextView lbTotal = productViewModel.findViewById(R.id.lbTotalProductViewModel);
        builder.setView(productViewModel);

        txtName.setText(product.getName());
        txtQuantity.setText(String.valueOf(product.getQuantity()));
        txtPrice.setText(String.valueOf(product.getPrice()));
        lbTotal.setText(String.valueOf(product.getTotal()));

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int quantity = Integer.parseInt(txtQuantity.getText().toString());
                    float price = Float.parseFloat(txtPrice.getText().toString());
                    float total = quantity * price;
                    lbTotal.setText(String.valueOf(total));
                }catch (Exception e){

                }
            }
        };
        txtQuantity.addTextChangedListener(textWatcher);
        txtPrice.addTextChangedListener(textWatcher);

        builder.setNegativeButton("CANCEL", null);
        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (txtQuantity.getText().toString().isEmpty() ||
                txtPrice.getText().toString().isEmpty()){
                    Toast.makeText(context, "  MISSING DATA", Toast.LENGTH_SHORT).show();
                }else {
                    product.setQuantity(Integer.parseInt(txtQuantity.getText().toString()));
                    product.setPrice(Float.parseFloat(txtPrice.getText().toString()));
                    notifyItemChanged(objects.indexOf(product));
                }
            }
        });
        return builder.create();
    }

    private AlertDialog confirmDelete(Product product){
       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       builder.setTitle("CONFIRM DELETE");
       //No se cierra al hacer click fuera
       builder.setCancelable(false);

       builder.setNegativeButton("CANCEL", null);
       builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               int position = objects.indexOf(product);
               objects.remove(product);
               notifyItemRemoved(position);
           }
       });
       return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ProductVH extends RecyclerView.ViewHolder{

        TextView lbName;
        TextView lbQuantity;

        ImageButton btnDelete;

        public ProductVH(@NonNull View itemView) {
            super(itemView);

            lbName = itemView.findViewById(R.id.lbNameProductViewHolder);
            lbQuantity = itemView.findViewById(R.id.lQuantityProductViewHolder);
            btnDelete = itemView.findViewById(R.id.btnDeleteProductViewHolder);
        }
    }
}
