package ro.skywebdesign.pocketheroes;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.gamepiecetutorial.R;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        
        // Set the hardware buttons to control the music
 		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
 		// Load the sound
 		Constants.SOUND_POOL = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
 		Constants.SOUND_POOL.setOnLoadCompleteListener(new OnLoadCompleteListener() {
 			@Override
 			public void onLoadComplete(SoundPool soundPool, int sampleId,
 					int status) {
 				Constants.SOUNDS_LOADED = true;
 			}
 		});
 		Constants.SOUND_RUN = Constants.SOUND_POOL.load(this, R.raw.run, 1);
 		Constants.SOUND_HIT = Constants.SOUND_POOL.load(this, R.raw.hit, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }
     
    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
    	
    	int itemId = item.getItemId();
    	
    	if(R.id.exitGame == itemId) {
    		this.finish();
            System.exit(0);
    	} else if(R.id.exitGame == itemId) {
    		Intent intent = getIntent();
        	finish();
        	Constants.GAME = null;
        	startActivity(intent);
    	} 
    	
    	return super.onOptionsItemSelected(item);
    	
    } 
}
