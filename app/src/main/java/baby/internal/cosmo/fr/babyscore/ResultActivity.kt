package baby.internal.cosmo.fr.babyscore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import baby.internal.cosmo.fr.babyscore.addmatch.AddMatchDialogActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ResultActivity : AppCompatActivity(), ResultDisplay {

    private lateinit var controller: ResultController
    private val resultAdapter = ResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val presenter = ResultPresenterImpl(this)

        val resultDataSourceController = ResultDataSourceImpl(FirebaseDatabase.getInstance())
        val interactor = ResultInteractorImpl(presenter, resultDataSourceController)
        resultDataSourceController.interactor = interactor
        controller = ResultControllerImpl(interactor)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = resultAdapter

        floatingActionButton.setOnClickListener {
            launch(CommonPool) {
                controller.addMatch()
            }
        }

        FirebaseMessaging.getInstance().subscribeToTopic("fanny")
    }

    override fun onResume() {
        super.onResume()
        launch(CommonPool) {
            controller.fetch()
        }
    }

    override fun showResults(results: List<Result>) {
        launch(UI) {
            resultAdapter.items = results
        }
    }

    override fun showAddMatchScreen() {
        launch(UI) {
            startActivity(AddMatchDialogActivity.getIntent(this@ResultActivity))
        }
    }

}
