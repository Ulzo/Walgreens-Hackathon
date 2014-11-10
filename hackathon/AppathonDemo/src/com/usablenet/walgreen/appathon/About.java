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
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.usablenet.walgreen.appathon.utils.Constants;

public class About extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.about);
        Resources res = getResources();
        String version  = res.getString(R.string.version);
        String buildNumber = res.getString(R.string.build_number);
        final TextView txtViewVersion = (TextView)findViewById(R.id.txtVersion);
        final TextView txtViewBuildNumber = (TextView)findViewById(R.id.txtBuild);
        txtViewVersion.setText(version);
        txtViewBuildNumber.setText(buildNumber);
        if(Constants.IS_PRODUCTION_DEMO)
        {
            txtViewBuildNumber.setVisibility(View.GONE);
        }
    }

}
