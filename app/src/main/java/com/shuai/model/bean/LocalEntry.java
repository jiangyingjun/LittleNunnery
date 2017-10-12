package com.shuai.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created 2017/10/11.\
 * <p>
 * parcelable 在内存中使用性能优于Serializable
 * <p>
 * parcelable 在熟读数据时候实在内存中直接进行读写的，而Serializable是通过IO流的形式将数据读写在硬盘上
 */

public class LocalEntry implements Parcelable {


    private String aa;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public LocalEntry() {
    }


    protected LocalEntry(Parcel in) {
        aa = in.readString();
    }

    public static final Creator<LocalEntry> CREATOR = new Creator<LocalEntry>() {
        @Override
        public LocalEntry createFromParcel(Parcel in) {
            return new LocalEntry(in);
        }

        @Override
        public LocalEntry[] newArray(int size) {
            return new LocalEntry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(aa);

    }
}



