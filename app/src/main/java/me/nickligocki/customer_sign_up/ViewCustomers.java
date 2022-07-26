package me.nickligocki.customer_sign_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ViewCustomers extends AppCompatActivity {

    private DBHelper db;
    RecyclerView rvCust;
    CustomerAdapter customerAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Customer> customerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);
        db = new DBHelper(this);
        customerList = db.getCustomerList();
        rvCust = findViewById(R.id.customerRV);
        rvCust.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvCust.setLayoutManager(layoutManager);
        customerAdapter = new CustomerAdapter(this, customerList, rvCust);
        rvCust.setAdapter(customerAdapter);
    }
}