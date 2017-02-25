package com.example.fahim.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is for decrement the quantity value of order.
     * @param view
     */
    public void decrement(View view){
        if(quantity==0) quantity = 0;
        else quantity = quantity - 1;
        displayQuantity(quantity);

    }

    /**
     * This method is for increment the quantity value of order.
     * @param view
     */
    public void increment(View view){
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is for displaying the order summary.
     * @param view
     */
    public void orderButtonPressed(View view) {
        displayOrderSummary(orderSummaryLines(checkBoxStatus()));
    }

    /**
     * This is for counting the price for total ordered cup of coffee.
     * @param price
     * @return total value.
     */
    private int totalPricePerCup(int price){
        return quantity * price ;
    }

    /**
     * This method is for building order summary instruction.
     * @return String of summary.
     */
    private String orderSummaryLines(boolean hasWhippedCream){
        String customerName = "Name: Mashrur Fahim";
        String addWhippedCream = "Add whipped cream? " + hasWhippedCream;
        String quantityText = "Quantity: " + quantity;
        String totalPriceText = "Total: $" + totalPricePerCup(5);
        String welcomeText = "Thank you!";

        String orderSummaryTextFull = customerName + "\n" + addWhippedCream + "\n" +  quantityText + "\n" + totalPriceText + "\n"
                + welcomeText ;
        return orderSummaryTextFull;
    }

    /**
     * Creating view by xml TextView reference and set text for quantity update.
     * @param number
     */
    public void displayQuantity(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.change_quantiy_text);
        quantityTextView.setText("" + number);
    }

    /**
     * Creating view by xml TextView reference and set text for order summary.
     * @param lines
     */
    public void displayOrderSummary(String lines){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.change_order_summary_text);
        orderSummaryTextView.setText(lines);
    }

    /**
     * Check the status of the whipped cream by finding its id reference.
     * @return boolea value of hasWhippedCream
     */
    public boolean checkBoxStatus(){
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        return hasWhippedCream;
    }
}
