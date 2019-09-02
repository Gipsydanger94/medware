package sidney.odingo.medwareasap.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sidney.odingo.medwareasap.Interface.ItemClickListener;
import sidney.odingo.medwareasap.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName,txtProductDescription,txtProductPrice;
    public ImageView imageView;
public ItemClickListener listener;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);


        imageView = itemView.findViewById(R.id.item_image);
        txtProductName= itemView.findViewById(R.id.item_name);
        txtProductDescription= itemView.findViewById(R.id.item_description);
        txtProductPrice= itemView.findViewById(R.id.item_price);
    }

    public void setItemClickListener(ItemClickListener Listener)
    {

        this.listener=listener;
    }

    @Override
    public void onClick(View view) {

        listener.onClick(view, getAdapterPosition(),false);

    }
}
