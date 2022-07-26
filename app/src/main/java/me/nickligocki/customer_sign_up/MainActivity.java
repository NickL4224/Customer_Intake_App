package me.nickligocki.customer_sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameText, lastNameText, emailText, phoneText, cityText, stateText;
    private Button submitButton, viewCustomersButton, exportButton;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firstNameText = findViewById(R.id.firstNameText);
        lastNameText = findViewById(R.id.lastNameText);
        emailText = findViewById(R.id.emailText);
        phoneText = findViewById(R.id.phoneText);
        cityText = findViewById(R.id.cityText);
        stateText = findViewById(R.id.stateText);
        submitButton = findViewById(R.id.submitButton);
        viewCustomersButton = findViewById(R.id.viewCustomersButton);
        exportButton = findViewById(R.id.exportButton);

        db = new DBHelper(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameText.getText().toString().trim();
                String lastName = lastNameText.getText().toString().trim();
                String email = emailText.getText().toString().trim();
                String phone = phoneText.getText().toString().trim();
                String city = cityText.getText().toString().trim();
                String state = stateText.getText().toString().trim();

                if(state.length() > 2) {
                    stateText.setError("(XX) format please");
                }



                Boolean checkInsert = db.insertCustomer(firstName, lastName, email, phone, city, state);
                
                if (checkInsert == true) {
                    Toast.makeText(MainActivity.this, "New User Entered: " + firstName + " " + lastName, Toast.LENGTH_LONG).show();
                    firstNameText.setText("");
                    lastNameText.setText("");
                    emailText.setText("");
                    phoneText.setText("");
                    cityText.setText("");
                    stateText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "FAILURE!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewCustomersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewCustomers.class));
            }
        });


        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.getAllCustomers();


                List<Customer> customers = new ArrayList<>();
                while (cursor.moveToNext()) {
                    Customer c = new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                    customers.add(c);
                }

                try {
                    writeToCSV(customers);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }


    public void writeToCSV(List<Customer> data) throws IOException {
        String DELIMITER = ",";
        String SEPARATOR = "\n";
        String HEADER = "Id,FirstName,LastName,Email,Phone,City,State";

        try {
            File csv = new File("\\C:\\Users\\18154\\Desktop\\Ecommerce\\customers.csv");
            FileWriter file = new FileWriter("customers.csv");
            file.append(HEADER);
            file.append(SEPARATOR);

            Iterator<Customer> i = data.iterator();
            while (i.hasNext()) {
                Customer c = (Customer) i.next();
                file.append((char) c.getId());
                file.append(DELIMITER);
                file.append(c.getFirstName());
                file.append(DELIMITER);
                file.append(c.getLastName());
                file.append(DELIMITER);
                file.append(c.getEmail());
                file.append(DELIMITER);
                file.append(c.getPhone());
                file.append(DELIMITER);
                file.append(c.getCity());
                file.append(DELIMITER);
                file.append(c.getState());
                file.append(SEPARATOR);
            }

            file.close();


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}