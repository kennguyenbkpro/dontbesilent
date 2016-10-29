package com.dontbesilent.dontbesilent.controller;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dontbesilent.dontbesilent.util.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by khanhnq2 on 29/10/2016.
 */

public class UploadMedia {
    private static final String TAG = "UploadMedia";

    public interface OnSuccessUploadListener {
        void onSuccess(Uri uploadedUri);
        void onFailure(Exception e);
        void onProgress(double p);
    }
    private void testUpload(){
        UploadMedia.uploadImage(Utils.getMediaDirectory() + "/test.jpg", new UploadMedia.OnSuccessUploadListener() {
            @Override
            public void onSuccess(Uri uploadedUri) {
                Log.e(TAG, "onSuccess: " + uploadedUri);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "onFailure: ");
            }

            @Override
            public void onProgress(double p) {
                Log.e(TAG, "onProgress: " + p);
            }
        });
    }

    public static void uploadImage(String filePath, final OnSuccessUploadListener onSuccessUploadListener){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://dontbesilent-29b7f.appspot.com");

        Uri file = Uri.fromFile(new File(filePath));
        StorageReference riversRef = storageRef.child("images/" + System.currentTimeMillis() + file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (onSuccessUploadListener != null){
                    onSuccessUploadListener.onFailure(e);
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                if (onSuccessUploadListener != null){
                    onSuccessUploadListener.onSuccess(taskSnapshot.getDownloadUrl());
                }
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                if (onSuccessUploadListener != null){
                    onSuccessUploadListener.onProgress((100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount());
                }
            }
        });
    }
}
