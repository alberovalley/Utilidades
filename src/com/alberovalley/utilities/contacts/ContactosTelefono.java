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

		HashMap<String, ContactInfo> listaContactos = new HashMap<String, ContactInfo>();
		// abrimos cursor para búsqueda de datos de contactos	
		Uri uri=ContactsContract.Contacts.CONTENT_URI;
		// ordenamos por el campo nombre, alfabéticamente
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
		Cursor cur=cr.query(uri, null, null, null, sortOrder);

		if(cur.getCount()>0){

			String id;
			String name;
			String photoId;
			// iteramos por los contactos
			while(cur.moveToNext()){

				
				ContactInfo c =new ContactInfo(cr);
				id = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup._ID));
				// abrimos un cursor para ver los números de teléfono que tenga el contacto actual
				 Cursor phones = cr.query(Phone.CONTENT_URI, null,
				            Phone.CONTACT_ID + " = " + id, null, null);
				 ContactInfo ci = new ContactInfo(cr);
		         name = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
		         photoId = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.PHOTO_ID));
 				 c.setId( Long.valueOf(id) );
 				 c.setDisplayName(name);
 				if (photoId != null){
					c.setPhotoId( Long.valueOf(photoId + "") );
					Uri contactPhotoUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, c.getId());
					c.setPhotoUri(contactPhotoUri);	
				}
 				 
				 while (phones.moveToNext()) {
			            String number = phones.getString(phones.getColumnIndex(Phone.NUMBER));
			            int type = phones.getInt(phones.getColumnIndex(Phone.TYPE));
			            
			            
			            switch (type) {
			                case Phone.TYPE_HOME:
			                    c.setHomePhoneNumber(number);
			                    break;
			                case Phone.TYPE_MOBILE:
			    				c.setMobilePhoneNumber(number);
			                    break;
			                case Phone.TYPE_WORK:
			                    c.setWorkPhoneNumber(number);
			                    break;
			                }
			        }
				 listaContactos.put(name, c);
			        phones.close();
				
				//phoneNum= cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.NUMBER));
				
				//c.setPhoneNumber(phoneNum);
				
				
			}
		}

		cur.close();
		return listaContactos;
	}
	
	
}
