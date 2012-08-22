package com.alberovalley.utilities.sharedpreferences;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Abstract base class that can be extended to provide classes that save 
 * {@link SharedPreferences} in the most efficient way possible. 
 * Decendent classes can optionally choose to backup some {@link SharedPreferences}
 * to the Google {@link BackupService} on platforms where this is available.
 */
public class SharedPreferenceSaver {
  
  protected Context context;
  public static final boolean DO_NOT_BACKUP = false;
  public static final boolean DO_BACKUP = true;
  
  protected SharedPreferenceSaver(Context context) {
    this.context = context;
  }
  
  /**
   * Save the Shared Preferences modified through the Editor object.
   * @param editor Shared Preferences Editor to commit.
   * @param backup Backup to the cloud if possible.
   */
  public void savePreferences(SharedPreferences.Editor editor, boolean backup) {
	  if(backup){
		  // backup @ cloud
	  }
	  editor.commit();
  }
}