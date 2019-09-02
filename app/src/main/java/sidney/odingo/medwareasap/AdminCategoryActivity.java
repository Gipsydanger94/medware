package sidney.odingo.medwareasap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView tShirts,sportsTshirts,femaleDresses,sweaters;
    private ImageView glasses, hats,purses,shoes;
    private ImageView headPhones,laptops,watches,mobilePhones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        tShirts = findViewById(R.id.t_shirts);
        sportsTshirts = findViewById(R.id.sport_t_shirts);
        femaleDresses = findViewById(R.id.female_dresses);
        sweaters = findViewById(R.id.sweater);

        glasses= findViewById(R.id.glasses);
        hats= findViewById(R.id.hats);
        purses= findViewById(R.id.purses_bag_wallets);
        shoes = findViewById(R.id.shoes);

        headPhones = findViewById(R.id.headphones);
        laptops = findViewById(R.id.laptops);
        tShirts = findViewById(R.id.t_shirts);
        watches = findViewById(R.id.watches);
        mobilePhones= findViewById(R.id.mobilesPhones);


        tShirts .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","tShirts");
                        startActivity(intent);


            }
        });

        sportsTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","sportsTshirts");
                startActivity(intent);



            }
        });

        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","femaleDresses");
                startActivity(intent);



            }
        });

        sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","sweaters");
                startActivity(intent);



            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","glasses");
                startActivity(intent);



            }
        });

        hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","hats");
                startActivity(intent);



            }
        });

        purses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","purses");
                startActivity(intent);



            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","shoes");
                startActivity(intent);



            }
        });

        headPhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","headphones");
                startActivity(intent);



            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","laptops");
                startActivity(intent);



            }


    });



        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","watches");
                startActivity(intent);



            }
        });

        mobilePhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent =new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Category","mobilePhones");
                startActivity(intent);



            }
        });
    }
}
