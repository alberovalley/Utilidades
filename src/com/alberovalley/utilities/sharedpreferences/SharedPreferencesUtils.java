package com.alberovalley.utilities.sharedpreferences;

import android.util.Log;
import android.widget.ArrayAdapter;

public class SharedPreferencesUtils {
	public static final String LOGTAG = "utilities";
	/*
	 * como split usa expresiones regulares, la mayoría de signos de puntuación y similares cuentan como 
	 * "caracteres especiales" y pueden dar resultados erróneos. Una cadena que sea difícil (o casi imposible) de que
	 * aparezca normalmente funcionará bien
	 */
	public static final String TOKEN="ñÑñ";
	
	public static String getTokenizedStringFromAdapter(ArrayAdapter<String> adapter){
		String tokenized = "";
		
		for (int i = 0; i < adapter.getCount(); i++){
			tokenized  += adapter.getItem(i) + TOKEN;
		}
		Log.d(LOGTAG, "tokenized = " + tokenized);
		return tokenized;
	}
	public static String[] tokenizedStringToArray(String tokenized){
		String[] array = null;
		if(tokenized !=null){
			if (tokenized.contains(TOKEN) || tokenized.length()>0){
				array = tokenized.split(TOKEN);
				Log.d(LOGTAG,"array tamaño = " +  array.length);
			}
		}
		return array;
	}
}
	
	
	

