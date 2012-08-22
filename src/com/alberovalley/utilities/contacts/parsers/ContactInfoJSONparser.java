package com.alberovalley.utilities.contacts.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;

import com.alberovalley.utilities.contacts.ContactInfo;

public class ContactInfoJSONparser {
	
	public static final String LOGTAG ="utilities.contacts.parsers";

	public String ContactInfo2JSON(ContactInfo ci){
		JSONObject o = new JSONObject();
		try {
			o.put(ContactInfo.DISPLAY_NAME, ci.getDisplayName());
			o.put(ContactInfo.HOME, ci.getHomePhoneNumber());
			o.put(ContactInfo.MOBILE, ci.getMobilePhoneNumber());
			o.put(ContactInfo.WORK, ci.getWorkPhoneNumber());
			o.put(ContactInfo.ID, ci.getId());
			o.put(ContactInfo.PHOTOID, ci.getPhotoId());
			o.put(ContactInfo.PHOTO_URI, ci.getPhotoUri());
		} catch (JSONException e) {
			Log.d(LOGTAG, "ContactInfo2JSON: " + e.getMessage());
		}
		
		
		return o.toString();
	}
	
	public ContactInfo JSON2ContactInfo (ContentResolver ctr, String json){
		ContactInfo ci = new ContactInfo(ctr);
		
		try {
			JSONObject o = new JSONObject(json);
			ci.setId(o.getLong(ContactInfo.ID));
			ci.setDisplayName(o.getString(ContactInfo.DISPLAY_NAME));
			ci.setHomePhoneNumber(o.getString(ContactInfo.HOME));
			ci.setMobilePhoneNumber(o.getString(ContactInfo.MOBILE));
			ci.setWorkPhoneNumber(o.getString(ContactInfo.WORK));
			ci.setPhotoId(o.getLong(ContactInfo.PHOTOID));
			ci.setPhotoUri( Uri.parse( o.getString( ContactInfo.PHOTO_URI) ) );
		} catch (JSONException e) {
			Log.d(LOGTAG, "JSON2ContactInfo: " + e.getMessage());
		}
		return ci;
	}
}
