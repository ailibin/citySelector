package com.aiitec.jpmj.entitylibary.model

import android.os.Parcel
import android.os.Parcelable

class Citys(): Parcelable {

    var id = 0

    var char: String? = null

    var name: String? = null

    var sortLetters: String? = null

    var datas: List<CharCitys>? = null

    var provinceCode = 0

    var provinceName: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        char = parcel.readString()
        name = parcel.readString()
        sortLetters = parcel.readString()
        datas = parcel.createTypedArrayList(CharCitys)
        provinceCode = parcel.readInt()
        provinceName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(char)
        parcel.writeString(name)
        parcel.writeString(sortLetters)
        parcel.writeTypedList(datas)
        parcel.writeInt(provinceCode)
        parcel.writeString(provinceName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Citys> {
        override fun createFromParcel(parcel: Parcel): Citys {
            return Citys(parcel)
        }

        override fun newArray(size: Int): Array<Citys?> {
            return arrayOfNulls(size)
        }
    }


}