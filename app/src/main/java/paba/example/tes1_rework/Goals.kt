package paba.example.tes1_rework

import android.os.Parcel
import android.os.Parcelable

data class Goals(
    var goalAmount: Long,
    val goalID: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(goalAmount)
        parcel.writeString(goalID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Goals> {
        override fun createFromParcel(parcel: Parcel): Goals {
            return Goals(parcel)
        }

        override fun newArray(size: Int): Array<Goals?> {
            return arrayOfNulls(size)
        }
    }
}