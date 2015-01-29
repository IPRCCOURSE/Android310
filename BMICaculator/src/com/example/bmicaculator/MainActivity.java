package com.example.bmicaculator;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends Activity {

	
	boolean isEng = true;
	private EditText etWeight;// = (EditText) findViewById(R.id.editW);
    private EditText etHeight; // = (EditText) findViewById(R.id.editH);
    private TextView WUnit;// = (TextView) findViewById(R.id.WUnit);
    private TextView HUnit;// = (TextView) findViewById(R.id.HUnit);
    private TextView BMI;// = (TextView) findViewById(R.id.textBMI);
    private TextView BMIInfo;// = (TextView) findViewById(R.id.BMIInfo);
    
    double weight = 100;
    double height = 100;
    
    double bmi;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        
        etWeight = (EditText) findViewById(R.id.editW);
         etHeight = (EditText) findViewById(R.id.editH);
         WUnit = (TextView) findViewById(R.id.WUnit);
         HUnit = (TextView) findViewById(R.id.HUnit);
         BMI = (TextView) findViewById(R.id.textBMI);
        BMIInfo = (TextView) findViewById(R.id.BMIInfo);
        
        etWeight.addTextChangedListener(WeightEditTextWatcher);
        etHeight.addTextChangedListener(HeightEditTextWatcher);
        
        RadioGroup taxGroup = (RadioGroup) findViewById(R.id.radioUnits);
        
        taxGroup.clearCheck();
        taxGroup.setOnCheckedChangeListener(UnitsChanged);
        
        RadioButton rb = (RadioButton)findViewById(R.id.raEng);
        
        rb.setChecked(true);
        		
        
        
        
        
        
        
    }
    private void CalBMI()
    {
    	
    	bmi = weight / (height * height);
    	
    	if (isEng)
    		bmi *= 703;
    	
    	
    	BMI.setText("" + bmi);
    	
    	String bmiInfo = "UnderWeight";
    	
    	if (bmi >18.5 && bmi < 24.9)
    		bmiInfo = "Normal";
    	
    	
    	if (bmi >=24.9 && bmi < 29.9)
    		bmiInfo = "Overweight";	
    	
    	
    	if (bmi >=29.9)
    		bmiInfo = "Obese";	
    	
    	
    	BMIInfo.setText(bmiInfo);
    	
    	if (isEng)
    	{
    		WUnit.setText("Pounds");
    		HUnit.setText("Inches");
    		
    		
    	}else
    	{
    		WUnit.setText("KiloGrams");
    		HUnit.setText("Meters");
    		
    	}
    	
    	
    }
    
    
    
    
    
    private OnCheckedChangeListener UnitsChanged =
 		   new OnCheckedChangeListener()
    {
 	   
 	   @Override
 	   public void onCheckedChanged(RadioGroup group, int checkedID){
 		   
 		   if (checkedID == R.id.raEng)
 			   isEng = true;
 		   
 		   if (checkedID == R.id.raMet)
 			   isEng = false; 
 		   
 		   
 		   CalBMI();
 	     
 		   
 		   
 		   
 	   }
 	   
 	   
 	   
 	   
    };
    
    private TextWatcher HeightEditTextWatcher = new TextWatcher() 
    {
       // called when the user enters a number
       @Override
       public void onTextChanged(CharSequence s, int start, 
          int before, int count) 
       {         
          // convert amountEditText's text to a double
          try
          {
             height = Double.parseDouble(s.toString());
          } // end try
          catch (NumberFormatException e)
          {
        	  height = 100; // default if an exception occurs
          } // end catch 

           CalBMI();
       } // end method onTextChanged

       @Override
       public void afterTextChanged(Editable s) 
       {
       } // end method afterTextChanged

       @Override
       public void beforeTextChanged(CharSequence s, int start, int count,
          int after) 
       {
       } // end method beforeTextChanged
    }; // end amountEditTextWatcher

 
    private TextWatcher WeightEditTextWatcher = new TextWatcher() 
    {
       // called when the user enters a number
       @Override
       public void onTextChanged(CharSequence s, int start, 
          int before, int count) 
       {         
          // convert amountEditText's text to a double
          try
          {
             weight = Double.parseDouble(s.toString());
          } // end try
          catch (NumberFormatException e)
          {
        	  weight = 100; // default if an exception occurs
          } // end catch 

           CalBMI();
       } // end method onTextChanged

       @Override
       public void afterTextChanged(Editable s) 
       {
       } // end method afterTextChanged

       @Override
       public void beforeTextChanged(CharSequence s, int start, int count,
          int after) 
       {
       } // end method beforeTextChanged
    }; // end amountEditTextWatcher



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
