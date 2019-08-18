package com.example.manguostore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class StoreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spCategory;
    Spinner spSubCategory;
    Spinner spSize;
    LinearLayout size, shoesize, viewGroup,quantity, amount;
    String category;
    String sub_category;
    String fit;

    //testing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        spCategory = (Spinner) findViewById(R.id.category_spin);
        spSubCategory = (Spinner) findViewById(R.id.subcategory_spin);
        spSize = (Spinner) findViewById(R.id.size_spin);
        size = (LinearLayout) findViewById(R.id.size);
        shoesize = (LinearLayout) findViewById(R.id.shoesize);
        viewGroup = (LinearLayout) findViewById(R.id.line_store);
        quantity = (LinearLayout) findViewById(R.id.quantity);
        amount = (LinearLayout) findViewById(R.id.amount);

        //adapter to link data to the spinner
        ArrayAdapter<CharSequence> adCategory = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        adCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adCategory);
        spCategory.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        category = parent.getItemAtPosition(position).toString();
        if(category.equals("Apparel")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.apparel,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Footwear")){
            size.setVisibility(view.INVISIBLE);
            shoesize.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.foot,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } if (category.equals("Sportswear")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sport,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Traditional")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.traditional,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Formal Wear")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.formal,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Accessories")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.accessories,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Watches and Jewelry")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.INVISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.accessories,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Cosmetics")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.INVISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cosmetics,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }if (category.equals("Vintage and Secondhand")){
            shoesize.setVisibility(view.INVISIBLE);
            size.setVisibility(view.VISIBLE);
            quantity.setVisibility(view.VISIBLE);
            amount.setVisibility(view.VISIBLE);
            ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,R.array.size,android.R.layout.simple_spinner_item);
            adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSize.setAdapter(adapter_size);
            spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    fit = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.vintage,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSubCategory.setAdapter(adapter);
            spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sub_category = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
