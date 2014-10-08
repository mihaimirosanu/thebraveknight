package ro.skywebdesign.pocketheroes;

import java.util.Random;

import ro.skywebdesign.pocketheroes.exception.ExceptionUtils;
import ro.skywebdesign.pocketheroes.exception.MapLoadingException;
import ro.skywebdesign.pocketheroes.model.Position;
import ro.skywebdesign.pocketheroes.model.map.MapObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gamepiecetutorial.R;

public class Utils {

	
	public static ProgressDialog SPINNER = null;
	public static void showSpinner(Context context, String message) {
		SPINNER = ProgressDialog.show(context, "Please wait ...", message, true);
		SPINNER.setCancelable(true);
	}
	public static void hideSpinner() {
		if(SPINNER != null) {
			SPINNER.dismiss();
		}
	}
	
	public static void showFatalError(Context ctx){
		Utils.showAlert("Error", "An error occured... We are sorry for the inconvenience", ctx);
	}
	
	public static void showAlert(String title, String message, Context ctx) {
    	AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
    	alertDialog.setTitle(title);
    	alertDialog.setMessage(message);
    	alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int which) {
    		dialog.dismiss();
    	}
    	});
    	alertDialog.setIcon(R.drawable.ic_launcher);
    	alertDialog.show();
	}
	
	public static void showOKCancelAlert(String title, String message, Context ctx, DialogInterface.OnClickListener okClickListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		
		builder.setTitle(title);
		builder.setMessage(message);
    	
		builder.setPositiveButton(R.string.ok, okClickListener);
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                
            }
        });
		AlertDialog alertDialog = builder.create();
    	alertDialog.setIcon(R.drawable.ic_launcher);
    	alertDialog.show();
			
	}
	
	public static void showToast(String text, Context context) {
		LayoutInflater inflater = ((MainActivity) context).getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast,
		                               (ViewGroup) ((MainActivity) context).findViewById(R.id.custom_toast_layout_id));

		TextView textView = (TextView) layout.findViewById(R.id.text);
		textView.setText(text);

		Toast toast = new Toast(((MainActivity) context).getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}
	
	public static Position getRandomPosition(MapObject[][] mapObjects) throws MapLoadingException {
		Random r = new Random();
		
		int count = 0;
		do {
			int randomRow = r.nextInt(10);
			int randomCol = r.nextInt(10);
			
			MapObject mapObject = mapObjects[randomRow][randomCol];
			
			if(mapObject.getClass().equals(MapObject.class)) {
				return new Position(randomRow, randomCol);
			}
			
			count++;
		}while(count < 10000);
		
		ExceptionUtils.handleException(new RuntimeException("cannot generate random position. Map too loaded!"));
    	throw new MapLoadingException();
	}
	
	public static boolean getRandomBoolean(){
		Random r = new Random();
		return r.nextInt(2) == 1;
	}
	
	public static void playSound(int soundId, Context context) {
		// Getting the user sound settings
		Activity activity = (Activity) context;
		AudioManager audioManager = (AudioManager) activity.getSystemService(Activity.AUDIO_SERVICE);
		float actualVolume = (float) audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = (float) audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;
		// Is the sound loaded already?
		if (Constants.SOUNDS_LOADED) {
			Constants.SOUND_POOL.play(soundId, volume, volume, 1, 0, 1f);
		}
	}
	public static int getRandom(int i) {
		Random r = new Random();
		return r.nextInt(i + 1);
	}
	
	public static void log(String tag, String message) {
		if(Constants.LOG_INFO) {
			Log.i(tag, message);
		}
	}
}
