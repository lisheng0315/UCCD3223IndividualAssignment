package com.example.kohindividualassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLoanAmount, editTextInterestRate, editTextRepayments;
    private Spinner spinnerLoanType;
    private Button buttonCalculate, buttonViewAmortization;
    private RecyclerView recyclerViewAmortization;
    private AmortizationAdapter amortizationAdapter;
    private List<AmortizationItem> amortizationItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLoanAmount = findViewById(R.id.editTextLoanAmount);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextRepayments = findViewById(R.id.editTextRepayments);
        spinnerLoanType = findViewById(R.id.spinnerLoanType);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonViewAmortization = findViewById(R.id.buttonViewAmortization);
        recyclerViewAmortization = findViewById(R.id.recyclerViewAmortization);

        recyclerViewAmortization.setLayoutManager(new LinearLayoutManager(this));
        amortizationItemList = new ArrayList<>();
        amortizationAdapter = new AmortizationAdapter(amortizationItemList);
        recyclerViewAmortization.setAdapter(amortizationAdapter);

        buttonCalculate.setOnClickListener(v -> {
            // Calculation logic here
        });

        buttonViewAmortization.setOnClickListener(v -> {
            amortizationItemList.clear();
            amortizationItemList.addAll(generateAmortizationSchedule());
            amortizationAdapter.notifyDataSetChanged();
            recyclerViewAmortization.setVisibility(View.VISIBLE);
        });
    }

    private List<AmortizationItem> generateAmortizationSchedule() {
        List<AmortizationItem> items = new ArrayList<>();

        double loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
        double interestRate = Double.parseDouble(editTextInterestRate.getText().toString()) / 100;
        int numberOfRepayments = Integer.parseInt(editTextRepayments.getText().toString());
        String loanType = spinnerLoanType.getSelectedItem().toString();

        double monthlyInstalment;
        if (loanType.equals("Personal loan")) {
            double R = interestRate / 12;
            monthlyInstalment = (loanAmount * (1 + R * numberOfRepayments)) / numberOfRepayments;
        } else {
            double R = interestRate / 12;
            monthlyInstalment = (loanAmount * R * Math.pow(1 + R, numberOfRepayments)) / (Math.pow(1 + R, numberOfRepayments) - 1);
        }

        double balance = loanAmount;
        for (int i = 1; i <= numberOfRepayments; i++) {
            double interestPaid = balance * (interestRate / 12);
            double principalPaid = monthlyInstalment - interestPaid;
            items.add(new AmortizationItem(i, balance, monthlyInstalment, interestPaid, principalPaid));
            balance -= principalPaid;
        }

        return items;
    }
}