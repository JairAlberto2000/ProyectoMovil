package com.example.proyecto

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

class Notificaciones : FirebaseMessagingService() {

    override fun onMessageReceived(mensajeRemoto: RemoteMessage) {
        super.onMessageReceived(mensajeRemoto)
        mensajeRemoto.notification?.let {
            enviarNotificacion(it.body)
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Token refrescado: $token")
        // Enviar el token al servidor (si es necesario)
    }

    private fun enviarNotificacion(cuerpoMensaje: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val intentPendiente = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val idCanal = "Canal_Defecto"
        val constructorNotificacion = NotificationCompat.Builder(this, idCanal)
            .setSmallIcon(R.drawable.ic_notification) // Asegúrate de que este ícono exista
            .setContentTitle("Nueva Reservación")
            .setContentText(cuerpoMensaje)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Añadir prioridad alta
            .setContentIntent(intentPendiente)

        val administradorNotificaciones = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val canal = NotificationChannel(idCanal, "Título legible del canal", NotificationManager.IMPORTANCE_HIGH)
            administradorNotificaciones.createNotificationChannel(canal)
        }

        administradorNotificaciones.notify(0, constructorNotificacion.build())
    }

    companion object {
        private const val TAG = "Notificaciones"
    }
}
