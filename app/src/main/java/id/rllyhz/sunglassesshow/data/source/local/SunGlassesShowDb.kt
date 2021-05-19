package id.rllyhz.sunglassesshow.data.source.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.rllyhz.sunglassesshow.data.source.local.entity.FavMovie
import id.rllyhz.sunglassesshow.data.source.local.entity.FavTVShow

@Database(entities = [FavMovie::class, FavTVShow::class], version = 1)
abstract class SunGlassesShowDb : RoomDatabase() {
    abstract fun dao(): SunGlassesShowDao

    companion object {
        private var instance: SunGlassesShowDb? = null

        fun getInstance(app: Application): SunGlassesShowDb =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    app,
                    SunGlassesShowDb::class.java,
                    "sunglassesshow.db"
                )
                    .build()
            }
    }
}