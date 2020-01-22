package com.example.defaultnotifications.defaultNotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.defaultnotifications.R
import com.example.defaultnotifications.application.MyApplication
import com.example.defaultnotifications.base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*

class DefaultNotificationActivity : BaseActivity() {
    private val NOTIFICATION_CHANNEL_ID = "1"
    private val NOTIFICATION_CHANNEL_ID_TWO = "2"
    private var notificationManager2: NotificationManager? = null

    /**Unique Id for Notification Channel*/
    private var notificationManager: NotificationManager? = null
    private var androidImage: Bitmap? = null
    override fun setLayout(): Int {
        return R.layout.activity_notification
    }

    override fun initView(savedInstanceState: Bundle?) {
        setClickListener()
        /**In this Method user get select certain notification to want*/
        setNotificationChannel()
        /**In this method Notification Channel is created*/
        getNotificationBuilder()
        /**In this method we cr*/
        setNotificationChannelTwo()

    }

    private fun setNotificationChannelTwo() {
        /**Create and Manage Notification Channels
        Starting in Android 8.0 (API level 26), all notifications must be assigned to a channel. For each channel,
        you can set the visual and auditory behavior that is applied to all notifications in that channel.*/
        notificationManager2 =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        /** a NotificationChannel object with a unique channel ID, a user-visible name, and an importance level.*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID_TWO,
                "Notification Channel created",
                NotificationManager.IMPORTANCE_HIGH
            )
            /**If you'd like to further customize your channel's default notification behaviors,
             * you can call methods such as enableLights(), setLightColor(), and setVibrationPattern() on the NotificationChannel.
             * But remember that once you create the channel,
             * you cannot change these settings and the user has final control of whether these behaviors are active.*/
            notificationChannel.description = "Hello Im Notification"
            notificationChannel.enableLights(true)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager2?.createNotificationChannel(notificationChannel)
            /**Here Notification Channel is Created*/
        }
    }

    private fun setNotificationChannel() {
        /**Create and Manage Notification Channels
        Starting in Android 8.0 (API level 26), all notifications must be assigned to a channel. For each channel,
        you can set the visual and auditory behavior that is applied to all notifications in that channel.*/
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        /**So the following code is blocked by a condition on the SDK_INT version:
         * Before you can deliver the notification on Android 8.0 and higher,
         * you must register your app's notification channel with the system by passing
         * an instance of NotificationChannel.
         * So the following code is blocked by a condition on the SDK_INT version:*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /** a NotificationChannel object with a unique channel ID, a user-visible name, and an importance level.*/
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
                /**Channel importance affects the interruption level of all notifications posted in the channel,
                 *  and you must specify it in the NotificationChannel constructor. You can use one of five importance levels,
                 *  ranging from IMPORTANCE_NONE(0) to IMPORTANCE_HIGH(4).
                 * The importance level you assign to a channel applies to all notification messages that you post to it.*/
            )

            /**If you'd like to further customize your channel's default notification behaviors,
             * you can call methods such as enableLights(), setLightColor(), and setVibrationPattern() on the NotificationChannel.
             * But remember that once you create the channel,
             * you cannot change these settings and the user has final control of whether these behaviors are active.*/
            notificationChannel.description = "Hello Im Notification"
            notificationChannel.enableLights(true)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager?.createNotificationChannel(notificationChannel)
            /**Here Notification Channel is Created*/
        }
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        /**To get started, you need to set the notification's content and channel using a NotificationCompat.Builder object.*/
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Hi I'm Notification")
            .setContentText("Hi there! Notification")
            .setSmallIcon(R.drawable.fb)
            .setAutoCancel(true)
            /**which automatically removes the notification when the user taps it*/
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        /** you must also set the priority with setPriority() to support Android 7.1 and lower.*/

        /** NotificationCompat.Builder constructor requires that you provide a channel ID.
         *  This is required for compatibility with Android 8.0 (API level 26) and higher, but is ignored by older versions.*/
    }

    private fun setClickListener() {
        defaultNotification.setOnClickListener {
            sendDefaultNotificationToUser()
            /**when user click this button, it sends default notification*/
        }
        smallNotification.setOnClickListener {
            sendSmallNotificationToUser()
            /**when user click this button, it sends small notification with samll image*/
        }
        bigNotification.setOnClickListener {
            sendBigNotificationToUser()
            /**when user click this button, it sends big notification with big image*/
        }
        combineNotification.setOnClickListener {
            sendCombineNotification()
            /**when user click this button, it sends combine notification with pending intent and small image and big image.*/
        }
        customDefaultNotification.setOnClickListener {
            sendCustomDefaultNotificationToUser()
        }
        customSmallNotification.setOnClickListener {
            sendCustomSmallNotificationToUser()
        }
        customBigNotification.setOnClickListener {
            sendCustomBigNotificationToUser()
        }
        customCombineNotification.setOnClickListener {
            sendCustomCombineNotificationToUser()
        }
    }

    private fun sendDefaultNotificationToUser() {
        val notificationBuilder: NotificationCompat.Builder = getNotificationBuilder()
        notificationBuilder.setContentTitle("Hi Im Default Notification")
            .setContentText("Im Content Text")
        /**To make the notification appear, call NotificationManager.notify(),
         * passing it a unique ID for the notification and the result of NotificationCompat.Builder.build(). */

        notificationManager?.notify(1, notificationBuilder.build())
        /**If you give different id to notify() it generates separate notification,
         * or else if you give same ids to different notify() it will replace the notification in existing notification.*/
    }

    private fun sendSmallNotificationToUser() {
        /**here only with small image*/
        androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.fitness)
        /**here we setting a image as small size using bitmap*/
        val notificationBuilder: NotificationCompat.Builder = getNotificationBuilder()
        notificationBuilder.setContentTitle("Hi I'm Small Notification")
            .setContentText("Hi there! Content From Small Notification")
            .setLargeIcon(androidImage)

        /**To make the notification appear, call NotificationManager.notify(),
         * passing it a unique ID for the notification and the result of NotificationCompat.Builder.build(). */

        notificationManager?.notify(2, notificationBuilder.build())
        /**If you give different id to notify() it generates separate notification,
         * or else if you give same ids to different notify() it will replace the notification in existing notification.*/
    }

    private fun sendBigNotificationToUser() {
        /**here big notification with different images.*/
        androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.bigimage)
        /**here we setting a image as big size using bitmap*/
        val smallImage = BitmapFactory
            .decodeResource(resources, R.drawable.fitness)
        /**here we setting a image as small size using bitmap*/
        val notificationBuilder: NotificationCompat.Builder = getNotificationBuilder()

        /*for big picture with expandable*/
        /**To provide even more information, you can also create large,
         *  expandable notifications by applying one of several notification templates as described on this page.*/

        /**To start, build a notification with all the basic content as described in Create a Notification. Then,
         * call setStyle() with a style object ''NotificationCompat.BigPictureStyle()''
         * and supply information corresponding to each template, as shown below.*/
        notificationBuilder.setStyle(
            NotificationCompat.BigPictureStyle().bigPicture(androidImage)
        ).setContentTitle("Hi I'm Big Notification")
            .setContentText("Hi there! Content From Big Notification")
            .setLargeIcon(smallImage)
        /**here we setting a image as small size using bitmap*/

        /**To make the notification appear, call NotificationManager.notify(),
         * passing it a unique ID for the notification and the result of NotificationCompat.Builder.build(). */

        notificationManager?.notify(3, notificationBuilder.build())
        /**If you give different id to notify() it generates separate notification,
         * or else if you give same ids to different notify() it will replace the notification in existing notification.*/
    }

    private fun sendCombineNotification() {
        /**here big notification with different images.*/
        androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.bigimage)
        /**here we setting a image as big size using bitmap*/
        val smallImage = BitmapFactory
            .decodeResource(resources, R.drawable.fitness)
        /**here we setting a image as small size using bitmap*/

        /**A Pending Intent specifies an action to take in the future.
         * It lets you pass a future Intent to another application and allow that application to execute
         * that Intent as if it had the same permissions as your application,
         * whether or not your application is still around when the Intent is eventually invoked.*/
        val updateIntent =
            Intent(MyApplication.getApplicationContext(), DefaultNotificationActivity::class.java)
        /**any of the flags as supported by Intent#fillIn to control
         * which unspecified parts of the intent that can be supplied when the actual send happens.*/
        updateIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val updatePendingIntent = PendingIntent.getActivity(
            this, 4,
            updateIntent, PendingIntent.FLAG_ONE_SHOT
        )

        val notificationBuilder: NotificationCompat.Builder = getNotificationBuilder()

        /*for big picture with expandable*/
        /**To provide even more information, you can also create large,
         *  expandable notifications by applying one of several notification templates as described on this page.*/

        /**To start, build a notification with all the basic content as described in Create a Notification. Then,
         * call setStyle() with a style object ''NotificationCompat.BigPictureStyle()''
         * and supply information corresponding to each template, as shown below.*/

        notificationBuilder.setStyle(
            NotificationCompat.BigPictureStyle().bigPicture(androidImage)
        ).addAction(R.drawable.fb, "Combine Notification Success", updatePendingIntent)
            .setContentTitle("Hi I'm Combine Notification")
            .setContentText("Hi there! Content From Combine Notification")
            .setLargeIcon(smallImage)

        /**To make the notification appear, call NotificationManager.notify(),
         * passing it a unique ID for the notification and the result of NotificationCompat.Builder.build(). */
        notificationManager?.notify(4, notificationBuilder.build())
        /**If you give different id to notify() it generates separate notification,
         * or else if you give same ids to different notify() it will replace the notification in existing notification.*/
    }

    private fun sendCustomDefaultNotificationToUser() {
        val notificationBuilder: NotificationCompat.Builder = getNotificationBuilder()
        notificationBuilder.setContentTitle("Hi Im Custom Default Notification")
            .setContentText("Im Content Text")
        /**To make the notification appear, call NotificationManager.notify(),
         * passing it a unique ID for the notification and the result of NotificationCompat.Builder.build(). */

        notificationManager?.notify(5, notificationBuilder.build())
        /**If you give different id to notify() it generates separate notification,
         * or else if you give same ids to different notify() it will replace the notification in existing notification.*/

    }

    private fun sendCustomSmallNotificationToUser() {
        androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.fitness)
        /**here we setting a image as small size using bitmap*/
        val notificationBuilder: NotificationCompat.Builder = getNotificationBuilder()
        notificationBuilder.setContentTitle("Hi I'm Custom Small Notification")
            .setContentText("Hi there! Content From Small Notification")
            .setLargeIcon(androidImage)

        /**To make the notification appear, call NotificationManager.notify(),
         * passing it a unique ID for the notification and the result of NotificationCompat.Builder.build(). */

        notificationManager?.notify(6, notificationBuilder.build())
        /**If you give different id to notify() it generates separate notification,
         * or else if you give same ids to different notify() it will replace the notification in existing notification.*/
    }

    private fun sendCustomBigNotificationToUser() {
        /**here big notification with different images.*/
        androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.bigimage)
        /**here we setting a image as small size using bitmap*/
        val smallImage = BitmapFactory
            .decodeResource(resources, R.drawable.fitness)
        /**android.widget.RemoteViews. A class that describes a view hierarchy that can be displayed in another process.
         * The hierarchy is inflated from a layout resource file,
         * and this class provides some basic operations for modifying the content of the inflated hierarchy.*/
        val remoteViews = RemoteViews(
            MyApplication.getApplicationContext().packageName,
            R.layout.custom_notification_big
        )
        /**Here we are inflating custom layout to show full size image,
         below we are setting values to the ids from layout*/
        remoteViews.setImageViewBitmap(R.id.largeIcon, smallImage)
        remoteViews.setImageViewBitmap(R.id.bigPicture, androidImage)
        remoteViews.setTextViewText(R.id.title, "Hi Im Custom Big Ex Notification")
        remoteViews.setTextViewText(R.id.content, "Im content text")
        /**android.widget.RemoteViews. A class that describes a view hierarchy that can be displayed in another process.
         * The hierarchy is inflated from a layout resource file,
         * and this class provides some basic operations for modifying the content of the inflated hierarchy.*/
        val contentViews = RemoteViews(
            MyApplication.getApplicationContext().packageName,
            R.layout.custom_notification_small
        )
        /**Here we are inflating custom layout to show full size image,
        below we are setting values to the ids from layout*/
        contentViews.setImageViewBitmap(R.id.largeIcon, smallImage)
        contentViews.setTextViewText(R.id.title, "Hi Im Custom Big Notification")
        contentViews.setTextViewText(R.id.content, "Im content text")
        val notifyBuilder1: NotificationCompat.Builder? =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID_TWO)
                .setSmallIcon(R.drawable.fitness)
                .setAutoCancel(true)
                .setCustomContentView(contentViews)
                .setCustomBigContentView(remoteViews)
                .setContentTitle("Hi Im Custom Big Notification")
                .setContentText("Im content text")
                .setWhen(System.currentTimeMillis())
        /**To make the notification appear, call NotificationManager.notify(),
         * passing it a unique ID for the notification and the result of NotificationCompat.Builder.build(). */

        notificationManager2?.notify(7, notifyBuilder1?.build())
        /**If you give different id to notify() it generates separate notification,
         * or else if you give same ids to different notify() it will replace the notification in existing notification.*/
    }

    private fun sendCustomCombineNotificationToUser() {
        val updateIntent = Intent(this, DefaultNotificationActivity::class.java)
        val updatePendingIntent = PendingIntent.getActivity(
            this, 2,
            updateIntent, PendingIntent.FLAG_ONE_SHOT)
        /**here big notification with different images.*/
        androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.bigimage)
        /**here we setting a image as small size using bitmap*/
        val smallImage = BitmapFactory
            .decodeResource(resources, R.drawable.fitness)
        /**android.widget.RemoteViews. A class that describes a view hierarchy that can be displayed in another process.
         * The hierarchy is inflated from a layout resource file,
         * and this class provides some basic operations for modifying the content of the inflated hierarchy.*/
        val remoteViews = RemoteViews(
            MyApplication.getApplicationContext().packageName,
            R.layout.custom_notification_big
        )
        /**Here we are inflating custom layout to show full size image,
        below we are setting values to the ids from layout*/
        remoteViews.setImageViewBitmap(R.id.largeIcon, smallImage)
        remoteViews.setImageViewBitmap(R.id.bigPicture, androidImage)
        remoteViews.setTextViewText(R.id.title, "Hi Im Custom Big Ex Notification")
        remoteViews.setTextViewText(R.id.content, "Im content text")
        /**android.widget.RemoteViews. A class that describes a view hierarchy that can be displayed in another process.
         * The hierarchy is inflated from a layout resource file,
         * and this class provides some basic operations for modifying the content of the inflated hierarchy.*/
        val contentViews = RemoteViews(
            MyApplication.getApplicationContext().packageName,
            R.layout.custom_notification_small
        )
        /**Here we are inflating custom layout to show full size image,
        below we are setting values to the ids from layout*/
        contentViews.setImageViewBitmap(R.id.largeIcon, smallImage)
        contentViews.setTextViewText(R.id.title, "Hi Im Custom Big Notification")
        contentViews.setTextViewText(R.id.content, "Im content text")
        val notifyBuilder1: NotificationCompat.Builder? =
            NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID_TWO)
                .setSmallIcon(R.drawable.fitness)
                .setAutoCancel(true)
                .setLargeIcon(smallImage)
                .setCustomContentView(contentViews)
                .setCustomBigContentView(remoteViews)
                .setContentTitle("Hi Im Custom Big Notification")
                .setContentText("Im content text")
                .setWhen(System.currentTimeMillis())
                .addAction(
                    R.drawable.fb,
                    "Big Notification Success",
                    updatePendingIntent)
        /**To make the notification appear, call NotificationManager.notify(),
         * passing it a unique ID for the notification and the result of NotificationCompat.Builder.build(). */

        notificationManager2?.notify(8, notifyBuilder1?.build())
        /**If you give different id to notify() it generates separate notification,
         * or else if you give same ids to different notify() it will replace the notification in existing notification.*/
    }
}