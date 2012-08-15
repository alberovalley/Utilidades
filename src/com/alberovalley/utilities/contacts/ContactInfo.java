package com.alberovalley.utilities.contacts;

import java.io.InputStream;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;

public class ContactInfo {
	
	private long id;
    private String displayName;
    private String phoneNumber;
    private long photoId;
    private Uri photoUri;
    private ContentResolver cr;
    

    public ContactInfo(ContentResolver contR) {
		super();
		this.cr = contR;
	}

	public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Uri getPhotoUri() {
        return this.photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, this.id);;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public long getPhotoId() {
		return photoId;
	}
    public Bitmap getContactPhoto(){
    	InputStream photoDataStream = Contacts.openContactPhotoInputStream(cr,this.photoUri);
    	Bitmap bMap = BitmapFactory.decodeStream(photoDataStream);
    	return bMap;
    }

}
