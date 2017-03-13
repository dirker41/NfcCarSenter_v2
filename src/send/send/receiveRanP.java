package send.send;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcF;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class receiveRanP extends Activity {
    private TextView mTextView_ReadMsg ;
	
	private NfcAdapter mAdapter = null;

    private static PendingIntent mPendingIntent;
	private static IntentFilter[] mFilters;
	private static String[][] mTechLists;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.receive_random_page);
	        
	      FindViews();
		  SetListensers();
		    
		    mAdapter = NfcAdapter.getDefaultAdapter(this);

	        // Create a generic PendingIntent that will be deliver to this activity. The NFC stack
	        // will fill in the intent with the details of the discovered tag before delivering to
	        // this activity.
	        mPendingIntent = PendingIntent.getActivity(this, 0,
	                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

	        // Setup an intent filter for all MIME based dispatches
	        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
	        try {
	            ndef.addDataType("*/*");
	        } catch (MalformedMimeTypeException e) {
	            throw new RuntimeException("fail", e);
	        }
	        mFilters = new IntentFilter[] {
	                ndef,
	        };

	        // Setup a tech list for all NfcF tags
	        mTechLists = new String[][] { new String[] { NfcF.class.getName() } };
	        
	 }
	 
	    private void FindViews() {
		 
	     
			mTextView_ReadMsg = (TextView) findViewById(R.id.receivePtextView1 ) ;
		       
	    } // FindViews()
		 
		 private void SetListensers() {
			  ;
			 
			 
		 } // SetListensers()

		 
		 void resolveIntent(Intent intent) {
		   	    String action = intent.getAction();
		   	    
		   	    // if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
		   	    if ( "android.nfc.action.NDEF_DISCOVERED".equals(action)) {
		   	    	
		   	    	
		            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		            NdefMessage[] msgs;


		            if (rawMsgs != null) {
		                msgs = new NdefMessage[rawMsgs.length];
		                for (int i = 0; i < rawMsgs.length; i++) {
		                    msgs[i] = (NdefMessage) rawMsgs[i];
		                } // for 
		            } // if
		            else {
		            // Unknown tag type
		              byte[] empty = new byte[] {};
		              NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
		              NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
		              msgs = new NdefMessage[] {msg};
		              
		            } // else
		            
		            
		            buildTagViews( msgs ) ;
		            
		            
		            
		    	} // if 
		  
		  } // resolveIntent()
		    
		  void buildTagViews(NdefMessage[] msgs) {
		        if (msgs == null || msgs.length == 0) {
		            return;
		        } // if
		        
		          
		        String str = CheckRNumber( msgs[0].getRecords()[0].getPayload() );
		        
		        mTextView_ReadMsg.setText( str );
		        /*
		        if ( str.length() >= 3 )
		          FormatTrans.sStr = str ;
		         else 
		          FormatTrans.sStr = "" ;
		        	 
		        	 
		          Intent xintent = new Intent();
		          xintent.setClass(receiveRanP.this, driveP.class);
		          startActivity(xintent);
		          */
		        
		      
		  } // buildTagViews
		  
		  private String CheckRNumber ( byte[] b ) {
			  
			  byte[] TempByte = FormatTrans.SubByteArray ( 
	        		   b,
	        		   3,
	        		   b.length ) ;
			  
			  
			  try {
			    return FormatTrans.Decryption(0 ,TempByte ) ;
			  } catch ( Exception e) {
				  return "error trans" ;
			  }
			  
			  
			  
		  } // CheckRNumber()
		 
		 @Override
		 public void onResume() {
		     super.onResume();
		     mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
		 }

		 @Override
		 public void onNewIntent(Intent intent) {
		  	mTextView_ReadMsg.setText("Discovered tag " + " with intent: " + intent);
		    resolveIntent(intent);
		    
		    // if "check" then jmp to send
		    // else if drive keep in this page  
		    
		 }

		 @Override
		 public void onPause() {
		     super.onPause();
		     mAdapter.disableForegroundDispatch(this);
		 }

}