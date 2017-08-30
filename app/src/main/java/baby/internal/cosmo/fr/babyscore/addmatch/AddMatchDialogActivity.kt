package baby.internal.cosmo.fr.babyscore.addmatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import baby.internal.cosmo.fr.babyscore.R


class AddMatchDialogActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) : Intent {
            return Intent(context, AddMatchDialogActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmatch)


    }

}

