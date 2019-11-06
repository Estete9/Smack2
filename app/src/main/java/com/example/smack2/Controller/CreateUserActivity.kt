package com.example.smack2.Controller

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.smack2.R
import com.example.smack2.Services.AuthService
import com.example.smack2.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUserActivity : AppCompatActivity() {

    var userAvatar = "profileDefault"
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        createSpinner.visibility = View.INVISIBLE
    }

    fun generateUserAvatarClicked(view: View) {

        val random = Random()
        val color = random.nextInt(2)
        val avatar = random.nextInt(28)

        if (color == 0) {
            userAvatar = "light$avatar"
        } else {
            userAvatar = "dark$avatar"
        }

        val resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)
        createAvatarImageView.setImageResource(resourceId)

    }

    fun generateColorClicked(view: View) {

        var random = Random()
        var r = random.nextInt(255)
        var g = random.nextInt(255)
        var b = random.nextInt(255)

        createAvatarImageView.setBackgroundColor(Color.rgb(r, g, b))

        val savedR = r.toDouble() / 255
        val savedG = g.toDouble() / 255
        val savedB = b.toDouble() / 255
        avatarColor = "[$savedR, $savedG, $savedB, 1]"
    }

    fun createUserClicked(view: View) {
        enableSpinner(true)
        val userName = createUserNameText.text.toString()
        val email = createEmailText.text.toString()
        val password = createPasswordText.text.toString()

        if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

            AuthService.registerUser(this, email, password) { registerSuccess ->
                if (registerSuccess) {
                    AuthService.loginUser(this, email, password) { loginSuccess ->
                        if (loginSuccess) {
                            AuthService.createUser(
                                this,
                                userName,
                                email,
                                userAvatar,
                                avatarColor
                            ) { createSuccess ->
                                if (createSuccess) {

                                    val userDataChange = Intent(BROADCAST_USER_DATA_CHANGE)
                                    LocalBroadcastManager.getInstance(this)
                                        .sendBroadcast(userDataChange)

                                    enableSpinner(false)
                                    Toast.makeText(
                                        this,
                                        "Account successfully created!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    finish()
                                } else {
                                    errorToast()
                                }
                            }
                        } else {
                            errorToast()
                        }
                    }
                } else {
                    errorToast()
                }

            }
        } else {
            Toast.makeText(
                this,
                "Make sure email, user name and password are filled in",
                Toast.LENGTH_LONG
            ).show()
            enableSpinner(false)
        }


    }

    fun errorToast() {
        Toast.makeText(this, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }

    fun enableSpinner(enable: Boolean) {
        if (enable) {
            createSpinner.visibility = View.VISIBLE
        } else {
            createSpinner.visibility = View.INVISIBLE

        }
        createUserBtn.isEnabled = !enable
        createAvatarImageView.isEnabled = !enable
        backgroundColorBtn.isEnabled = !enable


    }

}
