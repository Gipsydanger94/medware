package sidney.odingo.medwareasap;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import sidney.odingo.medwareasap.Model.Products;


public class ProductDetailsActivity extends AppCompatActivity
{
private ImageView productImage;
private ElegantNumberButton numberButton;
private TextView productPrice,productDescription,productName;
private String productID="ProductID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID=getIntent().getStringExtra("pid ");



        numberButton=findViewById(R.id.number_btn);
        productImage=findViewById(R.id.product_image_details);
        productName=findViewById(R.id.product_name_details);
        productDescription=findViewById(R.id.product_description_details);
        productPrice=findViewById(R.id.product_price_details);

        getProductDetails(productID);

    }

    private void getProductDetails(String productID)
    {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {



                if(dataSnapshot.exists())
                {
                    Products products =dataSnapshot.getValue(Products.class);

                    productName.setText(products.getPname());
                    productPrice.setText(products.getPrice());
                    productDescription.setText(products.getDescription());
                    Picasso.get().load(products.getImage()).into(productImage);


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

