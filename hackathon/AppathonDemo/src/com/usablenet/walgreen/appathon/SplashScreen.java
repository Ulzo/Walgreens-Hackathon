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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity{
    
    protected boolean _active = true;
    protected int _splashTime = 2000; // time to display the splash screen in ms
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
     // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    startActivity(new Intent(SplashScreen.this,PhotoLanding.class));
//                    stop();
                }
            }
        };
        splashTread.start();
    }

}
