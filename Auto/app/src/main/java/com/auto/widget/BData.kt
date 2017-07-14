package com.auto.widget

import android.os.Parcel
import android.os.Parcelable

/**
 * Banner shu
 */
data class BData(var parcel: Parcel?) : Parcelable, Banner.BrData {


    var url: String? = null
    var title: String? = null
    var sub_title: String? = null

    constructor() : this(null)

    init {
        parcel?.readString()
        parcel?.readString()
        parcel?.readString()
    }


    companion object CREATOR : Parcelable.Creator<BData> {
        override fun createFromParcel(source: Parcel?): BData {
            return BData(source)
        }

        override fun newArray(size: Int): Array<BData?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(url)
        dest?.writeString(title)
        dest?.writeString(sub_title)
    }

    override fun describeContents(): Int = 0


    override fun getBrUrl(): String? {
        return url
    }

    override fun getBrTitle(): String? {
        return title
    }

    override fun getBrSuTitle(): String? {
        return sub_title
    }

}