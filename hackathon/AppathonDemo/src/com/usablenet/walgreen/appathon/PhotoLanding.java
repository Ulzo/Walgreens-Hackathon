/*
* Copyright 2012 Walgreen Co. All rights reserved *

* Licensed under the Walgreens Developer Program and Portal Terms of Use and API License Agreement, Version 1.0 (the “Terms of Use”)
* You may not use this file except in compliance with the License.
* A copy of the License is located at https://developer.walgreens.com/page/terms-use
*
* This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
* express or implied. See the License for the specific language governing  permissions and limitations under the License.
*/
package com.usablenet.walgreen.appathon;

import com.usablenet.walgreen.appathon.sdk.WagCheckoutContextException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class PhotoLanding extends Activity implements
		View.OnClickListener {
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_landing_layout);
		// Clearing the AWS session if it is already initialized and selected
		// images
		LocalAlbumGallery.mUploadedImagesPathList.clear();
		if (LocalAlbumGallery.mApiContext != null) {
			try {
				LocalAlbumGallery.mApiContext.destroy();
			} catch (WagCheckoutContextException e) {
				e.printStackTrace();
			}
			LocalAlbumGallery.mApiContext = null;
		}
		
		
// *** Trying to add personal touch by adding personal name instead of 'Sign in'
//		Currently failing D;why is that
		/*if (SettingsMenu.FIRST_NAME != null){
			TextView tv1 = (TextView) findViewById(R.id.firstNameEdt);
			TextView tv2 = (TextView) findViewById(R.id.TextView02);
			String s = (String) tv1.getText();
			tv2.setText(s);
		}*/
	}

	@Override
	public void onClick(View pView) {
		int id = pView.getId();
// *** if you make it an if statement instead, then you can use the sign in phase
//     separate from the looking at albums phase. 
		if (id == R.id.btn_print_from_phone){
			
		}else if(id == R.id.btn_print_android_arrow){
			callPrintFromAndroid();
		}else if(id == R.id.btn_sign_in){
			callSignIn();
		}else if(id == R.id.btn_take_pic){
			callTakePicture();
		}else if (id == R.id.btn_modify){
			callPrintFromAndroid();
		}
	/*	switch (id) {
		
		case R.id.btn_print_from_phone:
		case R.id.btn_print_android_arrow:
			
		case R.id.Button01:
			callSignIn();
		break;
		

		}*/
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
			
	}


	private void callSignIn() {
		
		Intent intent = new Intent(this, SettingsMenu.class);
		startActivity(intent);
	}

	private void callPrintFromAndroid() {
		
		Intent intent = new Intent(this, LocalAlbumsList.class);
		startActivity(intent);
	}
	
	private void callTakePicture(){
		Intent intent = new Intent(this, CameraActivity.class);
		startActivity(intent);
	}
	

//---------------------- for about option menu------------------------
    
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.aboutmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
        case R.id.aboutoption:
            intent = new Intent(this, About.class);
            startActivity(intent);
            break;
        }
        return true;
    }
  // ------------------ end about option menu ------------------ 

}
