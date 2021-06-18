package `in`.geekofia.example.alarmmanager

import `in`.geekofia.example.alarmmanager.constants.ACTION_SCHEDULE_DIAL
import `in`.geekofia.example.alarmmanager.constants.KEY_SCHEDULE_DIAL
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {

    // The most important function to be overridden in a BroadcastReceiver is onReceive().
    // It is the place for receiving the broadcast sent from the system.
    // We can manipulate the data bundled in the received Intent by using the getXXXExtra() functions.
    override fun onReceive(context: Context?, intent: Intent?) {
        // It is strongly recommended to check the action field in Intent
        // to ensure the broadcast is coming from our assigned PendingIntent.
        // action type: ACTION_SCHEDULE_DIAL
        if (intent != null) when (intent.action) {
            ACTION_SCHEDULE_DIAL -> {
                val sampleData = intent.getStringExtra(KEY_SCHEDULE_DIAL)
                Toast.makeText(context, sampleData, Toast.LENGTH_LONG).show()
            }
            "SOME_OTHER_ACTION" -> {
                Toast.makeText(context, "SOME_OTHER_ACTION", Toast.LENGTH_LONG).show()
            }
        }
    }
}