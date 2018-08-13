package com.example.parasdhanta.mapofcontacts.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class DemoJobCreater implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        switch (tag){
            case ShowNotificationJob.TAG:
                return new ShowNotificationJob();
            default:
                return null;
        }

    }
}
