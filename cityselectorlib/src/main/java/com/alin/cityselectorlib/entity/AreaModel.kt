package com.aiitec.jpmj.entitylibary.model

import android.os.Parcel
import android.os.Parcelable

class AreaModel() :  Parcelable {

    var id: Long = 0
    var name: String? = null
    var parent_id: Long = -1

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as Long
        name = parcel.readString()
        parent_id = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeLong(parent_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "AreaModel(id=$id, name=$name, parent_id=$parent_id)"
    }

    companion object CREATOR : Parcelable.Creator<AreaModel> {
        override fun createFromParcel(parcel: Parcel): AreaModel {
            return AreaModel(parcel)
        }

        override fun newArray(size: Int): Array<AreaModel?> {
            return arrayOfNulls(size)
        }
    }


}