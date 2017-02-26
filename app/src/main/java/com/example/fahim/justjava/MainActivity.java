package com.example.fahim.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int pricePerCup = 5;
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
        if(quantity==100) return;
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is for displaying the order summary.
     * @param view
     */
    public void orderButtonPressed(View view) {
        String subject = getString(R.string.email_subject,personNameMethod());
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummaryLines(personNameMethod(), whippedCreamCheckBoxStatus(), chocolateCheckBoxStatus()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        displayOrderSummary(orderSummaryLines(personNameMethod(), whippedCreamCheckBoxStatus(), chocolateCheckBoxStatus()));
    }

    public int basePrice(boolean hasWhippedCream, boolean hasChocolateCream){
        int newPricePerCup = pricePerCup;
        if(hasWhippedCream) newPricePerCup = newPricePerCup + 1;
        if(hasChocolateCream) newPricePerCup = newPricePerCup + 2;
        return newPricePerCup;
    }
    /**
     * This is for counting the price for total ordered cup of coffee.
     * @return total value.
     */
    private int totalPricePerCup(){
        return quantity * basePrice(whippedCreamCheckBoxStatus(),chocolateCheckBoxStatus()) ;
    }

    /**
     * This method is for building order summary instruction.
     * @return String of summary.
     */
    private String orderSummaryLines(String name, boolean hasWhippedCream, boolean hasChocolate){
        String customerName = getString(R.string.customer_name, name);
        String addWhippedCream = getString(R.string.add_whipped_cream, hasWhippedCream);
        String addChocolate = getString(R.string.add_chocolate, hasChocolate);
        String quantityText = getString(R.string.quantity_email_text, quantity);
        String totalPriceText = getString(R.string.total_price, totalPricePerCup());
        String welcomeText = getString(R.string.welcome_text);

        String orderSummaryTextFull = customerName + "\n" + addWhippedCream + "\n" +  addChocolate + "\n" +  quantityText + "\n" + totalPriceText + "\n"
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
     * Fetch the value of Name;
     */
    public String personNameMethod(){
        EditText nameText = (EditText) findViewById(R.id.person_name);
        String nameValue = nameText.getText().toString();
        return nameValue;
    }
    /**
     * Check the status of the whipped cream by finding its id reference.
     * @return boolea value of hasWhippedCream
     */
    public boolean whippedCreamCheckBoxStatus(){
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        return whippedCreamCheckBox.isChecked();
    }
    /**
     * Check the status of the chocolate by finding its id reference.
     * @return boolea value of hasWhippedCream
     */
    public boolean chocolateCheckBoxStatus(){
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        return chocolateCheckBox.isChecked();
    }
}
