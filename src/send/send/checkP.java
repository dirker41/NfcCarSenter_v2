package send.send;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class checkP extends Activity implements CreateNdefMessageCallback {
	
	private NfcAdapter mAdapter = null;
	private NdefMessage mMessage;
	private String mMessageStr ;
	
	
	private TextView mTextView_SendMsg ;
	private Button mGoToReceiveBtn ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_page);
        
       
        FindViews();
        SetListensers();
        // mTextView_SendMsg.setText( "yoitrt" ) ;
        
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if ( mAdapter == null ) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        
	    // mMessageStr =  "hellow H R U ?" ;
	    mMessageStr =  "ask to connect!" ;
	    mMessage = ndefText( mMessageStr );
	    
	    mAdapter.setNdefPushMessage(ndefText( mMessageStr ) , checkP.this ) ;
	    // mAdapter.setNdefPushMessageCallback( this, this );
	    
	    
	    // mTextView_SendMsg.setText( "create" );
    }

    private void FindViews() {
		 
	     
		mTextView_SendMsg = (TextView) findViewById(R.id.checkPtextView1 ) ;
		mGoToReceiveBtn = (Button)findViewById(R.id.CPB1);  
	       
	 } // FindViews()
	
	private void SetListensers() {
		// mTextView_SendMsg.setText( "123" ) ;
		mGoToReceiveBtn.setOnClickListener( gotoreceive );
		 
		 
	 } // SetListensers()
	
	private Button.OnClickListener gotoreceive = new OnClickListener() {
        public void onClick(View v) {
        	;
          // Perform action on click
        	
          Intent intent = new Intent();
          intent.setClass(checkP.this, receiveRanP.class);
          startActivity(intent);	
            
        } // onClick()
    }; // register button
	
	
	@Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        
		mTextView_SendMsg.setText( "¶Ç°e§¹²¦" );
		
		
		
		
        return mMessage;
    }
	
	
	private NdefMessage ndefText( String str ) {
		  byte[] ndefByte = FormatTrans.ndefTextByte( str ) ;
			
		   NdefRecord[] records = new NdefRecord[1];
			
			
		   try{
			 // records[0] = new NdefRecord( Bmessage ) ;
			 records[0] = new NdefRecord( ndefByte ) ;
		   } catch ( Exception e ) {
				
				// Toast.makeText(P2P.this, "ay" ,Toast.LENGTH_SHORT ).show();
				finish();
		   }
			
			
		   NdefMessage message = new NdefMessage(records);
		   return message;
	} // ndefText()
	
	
	void processIntent(Intent intent) {
        // textView = (TextView) findViewById(R.id.textView);
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        
    }
	
	
	@Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
    	
    	mTextView_SendMsg.setText( "onNewIntent" );
        setIntent(intent);
    }

}
