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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public abstract class ImagesListAdaptor extends BaseAdapter{

    private final ArrayList<File> mFileArray;
    private Activity mActivity;
    public abstract void onImageSelected(File imageFile, boolean isChecked);
    public ImagesListAdaptor(Activity activity, ArrayList<File> fileArray) {
        
        mActivity = activity;
        mFileArray = fileArray;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFileArray.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        
        if(convertView == null)
        {
            vi = (View) LayoutInflater.from(mActivity).inflate(R.layout.listview, null);
            holder = new ViewHolder();
            holder.imageToUpload = (ImageView) vi.findViewById(R.id.imageView1);
            holder.checkBox = (CheckBox)vi.findViewById(R.id.checkBox1);
            vi.setTag(holder);
        }
        else
        {
            holder =(ViewHolder) vi.getTag();
        }
        
        File imgFile = new  File(mFileArray.get(position).getAbsolutePath());
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inSampleSize = 2;
//        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        System.out.println("***File Name : "+imgFile.getAbsolutePath());
        holder.imageToUpload.setImageBitmap(getPreview(imgFile));
        holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               
                onImageSelected(mFileArray.get(position),isChecked);
            }
        });
        
        return vi;
    }
    Bitmap getPreview(File image ) {

        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(image.getPath(), bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
            return null;

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;
        return BitmapFactory.decodeFile(image.getPath());     
    }
    private static class ViewHolder
    {
        ImageView imageToUpload;
        CheckBox checkBox;
    }

}
