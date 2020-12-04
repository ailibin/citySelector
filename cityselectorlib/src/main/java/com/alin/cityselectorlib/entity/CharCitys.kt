package com.aiitec.jpmj.entitylibary.model

import android.os.Parcel
import android.os.Parcelable

class CharCitys() : Parcelable {

    var id: Long = 0
    var name: String? = null
    var parentId: Long = -1
    var pinyin: String? = null
    var char: String? = null
    var area_child: List<AreaModel>? = null


    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as Long
        name = parcel.readString()
        parentId = parcel.readLong()
        pinyin = parcel.readString()
        char = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeLong(parentId)
        parcel.writeString(pinyin)
        parcel.writeString(char)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "CharCitys(id=$id, name=$name, parentId=$parentId, pinyin=$pinyin, char=$char, area_child=$area_child)"
    }

    companion object CREATOR : Parcelable.Creator<CharCitys> {
        override fun createFromParcel(parcel: Parcel): CharCitys {
            return CharCitys(parcel)
        }

        override fun newArray(size: Int): Array<CharCitys?> {
            return arrayOfNulls(size)
        }
    }


}