package send.send;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;


public class SenterActivity extends Activity {
    /** Called when the activity is first created. */
	private Button mRegisterBtn ;
	private Button mLoginBtn ;
	private Button mCheckBtn ;
    private Button mDriveBtn ;
    private Button mCheatBtn ;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        FindViews();
        SetListeners();
    }
    
    
    private void FindViews() {
    	
    	mRegisterBtn = (Button)findViewById(R.id.register);  
    	mLoginBtn = (Button)findViewById(R.id.login);    
    	mCheckBtn = (Button)findViewById(R.id.check);  
    	mDriveBtn = (Button)findViewById(R.id.drive);
    	mCheatBtn = (Button)findViewById(R.id.cheat);
        
    } // findViews()
    
    private void SetListeners() {
    	
    	mRegisterBtn.setOnClickListener( register );
    	mLoginBtn.setOnClickListener( login );
    	mCheckBtn.setOnClickListener( check );
    	mDriveBtn.setOnClickListener( drive );
    	mCheatBtn .setOnClickListener( cheat );
    } // setListeners()
    
    private Button.OnClickListener register = new OnClickListener() {
        public void onClick(View v) {
        	;
          // Perform action on click
        	
          // Intent intent = new Intent();
          // intent.setClass(LabCar.this, TagWrite.class);
          // startActivity(intent);	
            
        } // onClick()
    }; // register button
    
    private Button.OnClickListener login = new OnClickListener() {
        public void onClick(View v) {
        	;
          // Perform action on click
        	
          // Intent intent = new Intent();
          // intent.setClass(LabCar.this, TagWrite.class);
          // startActivity(intent);	
            
        } // onClick()
    }; // login button
    
    
    private Button.OnClickListener check = new OnClickListener() {
        public void onClick(View v) {
        	
          // Perform action on click
          
          Intent intent = new Intent();
          intent.setClass(SenterActivity.this, checkP.class);
          startActivity(intent);	
          
        } // onClick()
    }; // check button
    
    private Button.OnClickListener drive = new OnClickListener() {
        public void onClick(View v) {
        	
          // Perform action on click
        	
        	Intent intent = new Intent();
            intent.setClass(SenterActivity.this, driveP.class);
            startActivity(intent);
            
        } // onClick()
    }; // drive button
    
    private Button.OnClickListener cheat = new OnClickListener() {
        public void onClick(View v) {
        	
          // Perform action on click
        	
        	Intent intent = new Intent();
            intent.setClass(SenterActivity.this, receiveRanP.class);
            startActivity(intent);
            
        } // onClick()
    }; // drive button
    

    
}