package com.example.calculatingelectricitybill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUnitCharges;
    Button btnCalculate;
    TextView tvTotalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUnitCharges = findViewById(R.id.etUnitCharges);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvTotalBill = findViewById(R.id.tvTotalBill);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

    }

    private void calculateBill() {
        String strUnitCharges = etUnitCharges.getText().toString();

        if (!strUnitCharges.isEmpty()) {
            try {
                Double unitCharges = Double.parseDouble(strUnitCharges);
                Double totalBill = 0.0;

                // Calculate total bill based on unit charges

                if (unitCharges <= 50) {
                    totalBill = unitCharges * 0.50;
                } else if (unitCharges <= 150) {
                    totalBill = 50 * 0.50 + (unitCharges - 50) * 0.75;
                } else if (unitCharges <= 250) {
                    totalBill = 50 * 0.5 + 100 * 0.75 + (unitCharges - 150) * 1.20;
                } else {
                    totalBill = 50 * 0.5 + 100 * 0.75 + 250 * 1.20 + (unitCharges - 250) * 1.50;
                }

                // Add a 20% surcharge
                totalBill = totalBill * 1.2;

                // Display the total bill
                tvTotalBill.setText("Total Electricity Bill: BDT " + String.format("%.2f", totalBill));

            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid number
                Toast.makeText(MainActivity.this, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle the case where the input is empty
            Toast.makeText(MainActivity.this, "Please enter the electricity unit charges.", Toast.LENGTH_SHORT).show();
        }

    }


}
