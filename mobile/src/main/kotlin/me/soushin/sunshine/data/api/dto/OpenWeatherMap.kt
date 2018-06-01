package me.soushin.sunshine.data.api.dto

import android.os.Parcel
import android.os.Parcelable

data class Forecasts(var city: City, var cod: Int, var list: List<Forecast>) : Parcelable {

    constructor(src: Parcel) : this(
            city = src.readParcelable(City::class.java.classLoader),
            cod = src.readInt(),
            list = src.createTypedArrayList(Forecast.CREATOR)
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(city, flags)
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

data class City(var name: String, var country: String) : Parcelable {

    constructor(src: Parcel) : this(
            name = src.readString(),
            country = src.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(country)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<City> = object : Parcelable.Creator<City> {
            override fun createFromParcel(source: Parcel): City? {
                return City(source)
            }

            override fun newArray(size: Int): Array<out City?>? {
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

data class CurrentWeather(var weather: List<Weather>, var main: Main, var dt: Long) : Parcelable {

    constructor(src: Parcel) : this(
            weather = src.createTypedArrayList(Weather.CREATOR),
            main = src.readParcelable(Main::class.java.classLoader),
            dt = src.readLong()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeList(weather)
        dest?.writeParcelable(main, flags)
        dest?.writeLong(dt)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CurrentWeather> = object : Parcelable.Creator<CurrentWeather> {
            override fun createFromParcel(source: Parcel): CurrentWeather? {
                return CurrentWeather(source)
            }

            override fun newArray(size: Int): Array<out CurrentWeather?>? {
                return arrayOfNulls(size)
            }
        }
    }

}

data class Main(var temp: Float, var pressure: Float, var humidity: Float, var tempMin: Float, var tempMax: Float) : Parcelable {

    constructor(src: Parcel) : this(
            temp = src.readFloat(),
            pressure = src.readFloat(),
            humidity = src.readFloat(),
            tempMin = src.readFloat(),
            tempMax = src.readFloat()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeFloat(temp)
        dest?.writeFloat(pressure)
        dest?.writeFloat(humidity)
        dest?.writeFloat(tempMin)
        dest?.writeFloat(tempMax)
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
