package `in`.geekofia.example.alarmmanager

import `in`.geekofia.example.alarmmanager.constants.ACTION_SCHEDULE_DIAL
import `in`.geekofia.example.alarmmanager.constants.ALARM_DELAY_IN_SECOND
import `in`.geekofia.example.alarmmanager.constants.KEY_SCHEDULE_DIAL
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setup() {
        // AlarmManager instance
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Intent
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.action = ACTION_SCHEDULE_DIAL
        // Intent extras
        intent.putExtra(KEY_SCHEDULE_DIAL, "Sample value")

        /**
         * PendingIntent
         *
         * requestCode
         * -----------
         * The request code can be treated as an identifier for different PendingIntent tokens with the same Intent.
         * In other words, it is only useful when you want multiple PendingIntent to have the same Intent.
         *
         * flags
         * -----
         * Flags indicates how system should handle the new and existing PendingIntents
         * that have the same Intent.
         * 0 indicates that system will use its default way to handle the creation of PendingIntent.
         * The following are some examples:
         * FLAG_UPDATE_CURRENT
         * FLAG_CANCEL_CURRENT
         * FLAG_IMMUTABLE
         **/
        // Retrieve a PendingIntent that will perform a broadcast by calling Context#sendBroadcast(Intent).
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        //
        /**
         * Alarm time
         *
         * Real Time Clock (Real time since 1st January, 1970)
         * ---------------------------------------------------
         * val alarmTimeAtUTC = System.currentTimeMillis() + 1 * 1_000L
         * alarmManager.setExact(AlarmManager.RTC, alarmTimeAtUTC, pendingIntent)
         * alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeAtUTC, pendingIntent)
         *
         * Elapsed Real Time (since device is booted)
         * ------------------------------------------
         * val alarmTimeAfterDeviceIsBooted = 1 * 1_000L
         * alarmManager.setExact(AlarmManager.ELAPSED_REALTIME, alarmTimeAfterDeviceIsBooted, pendingIntent)
         * alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, alarmTimeAfterDeviceIsBooted, pendingIntent)
         **/
        val alarmTimeAtUTC = System.currentTimeMillis() + ALARM_DELAY_IN_SECOND * 1_000L

        // By default, AlarmManager fires an alarm only when
        // device is not sleeping (RTC, ELAPSED_REALTIME).
        // In order to have an alarm goes off during device is sleeping,
        // the type of alarm must be set to either (RTC_WAKE_UP, ELAPSED_REALTIME_WAKEUP).

        // Set with system Alarm Service
        // Other possible functions: setExact() / setRepeating() / setWindow(), etc
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTimeAtUTC,
            pendingIntent
        )
    }
}