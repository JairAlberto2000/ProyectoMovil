package com.example.proyecto

import android.os.Parcel
import android.os.Parcelable

data class Platillo(

    val nombre: String?,
    val precio: Double,
    //val infoNutricional: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {

    }

    companion object CREATOR : Parcelable.Creator<Platillo> {
        override fun createFromParcel(parcel: Parcel): Platillo {
            return Platillo(parcel)
        }

        override fun newArray(size: Int): Array<Platillo?> {
            return arrayOfNulls(size)
        }
    }
}