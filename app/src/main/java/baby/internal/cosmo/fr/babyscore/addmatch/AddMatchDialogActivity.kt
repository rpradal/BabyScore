package baby.internal.cosmo.fr.babyscore.addmatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import baby.internal.cosmo.fr.babyscore.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_addmatch.*


class AddMatchDialogActivity : AppCompatActivity(), AddMatchDisplay {
    companion object {

        fun getIntent(context: Context): Intent {
            return Intent(context, AddMatchDialogActivity::class.java)
        }

    }
    lateinit var controller: AddMatchController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val addMatchPresenterImpl = AddMatchPresenterImpl(this)
        val addMatchDataSourceControllerImpl = AddMatchDataSourceControllerImpl(FirebaseDatabase.getInstance())
        controller = AddMatchControllerImpl(AddMatchInteractorImpl(addMatchPresenterImpl, addMatchDataSourceControllerImpl))
        setContentView(R.layout.activity_addmatch)

        addButton.setOnClickListener { controller.addMatch(getInputData()) }
    }

    override fun dismiss() {
        finish()
    }

    private fun getInputData(): AddMatchInput {
        return AddMatchInput(
                firstTeamFirstPlayerTextView.toStr(),
                firstTeamSecondPlayerTextView.toStr(),
                firstTeamScore.toStr(),
                secondTeamFirstPlayerTextView.toStr(),
                secondTeamSecondPlayerTextView.toStr(),
                secondTeamScoreTextView.toStr())

    }

    override fun showInvalidInput() {
        Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
    }

    private fun EditText.toStr() = this.text.toString()

}

interface AddMatchDisplay {
    fun showInvalidInput()
    fun dismiss()

}
