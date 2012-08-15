package com.alberovalley.utilities.sharedpreferences;

import android.util.Log;
import android.widget.ArrayAdapter;

public class SharedPreferencesUtils {
	public static final String LOGTAG = "utilities";
	public static final String TOKEN="ñÑñ";
	
	public static String getTokenizedStringFromAdapter(ArrayAdapter<String> adapter){
		String tokenized = "";
		
		for (int i = 0; i < adapter.getCount(); i++){
			tokenized  += adapter.getItem(i) + TOKEN;
		}
		//tokenized = tokenized.substring(0, tokenized.lastIndexOf(TOKEN)-1);
		Log.d(LOGTAG, "tokenized = " + tokenized);
		return tokenized;
	}
	public static String[] tokenizedStringToArray(String tokenized){
		String[] array = null;
		if (tokenized.contains(TOKEN) || tokenized.length()>0)
			array = tokenized.split(TOKEN);
		
		Log.d(LOGTAG,"array tamaño = " +  array.length);
		return array;
	}
	
	
	
	
}
