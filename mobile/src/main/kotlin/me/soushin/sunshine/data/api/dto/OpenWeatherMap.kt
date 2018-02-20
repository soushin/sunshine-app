package me.soushin.sunshine.data.api.dto

import android.os.Parcel
import android.os.Parcelable

data class Forecasts(var cod: Int, var list: List<Forecast>) : Parcelable {

    constructor(src: Parcel) : this(
            cod = src.readInt(),
            list = src.createTypedArrayList(Forecast.CREATOR)
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(cod)
        dest?.writeList(list)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Forecasts> = object : Parcelable.Creator<Forecasts> {
            override fun createFromParcel(source: Parcel): Forecasts? {
                return Forecasts(source)
            }

            override fun newArray(size: Int): Array<out Forecasts?>? {
                return arrayOfNulls(size)
            }
        }
    }
}

data class Forecast(var dt: Long, var temp: Temp, var weather: List<Weather>) : Parcelable {

    constructor(src: Parcel) : this(
            dt = src.readLong(),
            temp = src.readParcelable(Temp::class.java.classLoader),
            weather = src.createTypedArrayList(Weather.CREATOR)
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(dt)
        dest?.writeParcelable(temp, flags)
        dest?.writeList(weather)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Forecast> = object : Parcelable.Creator<Forecast> {
            override fun createFromParcel(source: Parcel): Forecast? {
                return Forecast(source)
            }

            override fun newArray(size: Int): Array<out Forecast?>? {
                return arrayOfNulls(size)
            }
        }
    }
}

data class Temp(var day: Float, var min: Float, var max: Float, var night: Float, var eve: Float,
                var morn: Float) : Parcelable {

    constructor(src: Parcel) : this(
            day = src.readFloat(),
            min = src.readFloat(),
            max = src.readFloat(),
            night = src.readFloat(),
            eve = src.readFloat(),
            morn = src.readFloat()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeFloat(day)
        dest?.writeFloat(min)
        dest?.writeFloat(max)
        dest?.writeFloat(night)
        dest?.writeFloat(eve)
        dest?.writeFloat(morn)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Temp> = object : Parcelable.Creator<Temp> {
            override fun createFromParcel(source: Parcel): Temp? {
                return Temp(source)
            }

            override fun newArray(size: Int): Array<out Temp?>? {
                return arrayOfNulls(size)
            }
        }
    }
}

data class Weather(var id: Int, var main: String, var description: String, var icon: String) : Parcelable {

    constructor(src: Parcel) : this(
            id = src.readInt(),
            main = src.readString(),
            description = src.readString(),
            icon = src.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(main)
        dest?.writeString(description)
        dest?.writeString(icon)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Weather> = object : Parcelable.Creator<Weather> {
            override fun createFromParcel(source: Parcel): Weather? {
                return Weather(source)
            }

            override fun newArray(size: Int): Array<out Weather?>? {
                return arrayOfNulls(size)
            }
        }
    }
}