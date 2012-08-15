package com.alberovalley.utilities.contacts;

import java.util.HashMap;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.util.Log;


public class ContactosTelefono {

	public static final String LOGTAG ="utilities.contacts";
	
	ContentResolver cr;
	public ContactosTelefono(ContentResolver cr){
		this.cr = cr;
	}
	public boolean addContacto(Activity ac, String nombre){
		boolean resultado = false;
		
        return resultado;
	}
	
	
	public HashMap<String, ContactInfo> getContactos(){
		//HashMap<String, Long> contactos = new HashMap<String, Long>();
		HashMap<String, ContactInfo> listaContactos = new HashMap<String, ContactInfo>();
		// abrimos cursor para búsqueda de datos de contactos
		
		Uri uri=ContactsContract.Contacts.CONTENT_URI;
		//ContentResolver cr=getContentResolver();
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
		Cursor cur=cr.query(uri, null, null, null, sortOrder);

		if(cur.getCount()>0){

			String id;
			String name;
			String photoId;
			//String phoneNum;
			while(cur.moveToNext()){

				
				ContactInfo c =new ContactInfo(cr);
				id = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup._ID));
				
				 Cursor phones = cr.query(Phone.CONTENT_URI, null,
				            Phone.CONTACT_ID + " = " + id, null, null);
				 while (phones.moveToNext()) {
			            String number = phones.getString(phones.getColumnIndex(Phone.NUMBER));
			            int type = phones.getInt(phones.getColumnIndex(Phone.TYPE));
			            switch (type) {
			                case Phone.TYPE_HOME:
			                    // do something with the Home number here...
			                    break;
			                case Phone.TYPE_MOBILE:
			                    // do something with the Mobile number here...
			                	ContactInfo ci = new ContactInfo(cr);
			                	name = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
			    				photoId = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.PHOTO_ID));
			    				c.setId( Long.valueOf(id) );
			    				c.setDisplayName(name);
			    				c.setPhoneNumber(number);
			    				if (photoId != null){
			    					c.setPhotoId( Long.valueOf(photoId + "") );
			    					Uri contactPhotoUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, c.getId());
			    					c.setPhotoUri(contactPhotoUri);
			    					
			    					
			    				}
			    				listaContactos.put(name, c);
			    				Log.d(LOGTAG, "contactos móvil: " + number );
			                    break;
			                case Phone.TYPE_WORK:
			                    // do something with the Work number here...
			                    break;
			                }
			        }
			        phones.close();
				
				//phoneNum= cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.NUMBER));
				
				//c.setPhoneNumber(phoneNum);
				
				
			}
		}

		cur.close();
		return listaContactos;
	}
	
	
}
