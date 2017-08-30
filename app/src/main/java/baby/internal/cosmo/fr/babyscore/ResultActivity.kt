package baby.internal.cosmo.fr.babyscore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import baby.internal.cosmo.fr.babyscore.addmatch.AddMatchDialogActivity
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ResultActivity : AppCompatActivity(), ResultDisplay {

    lateinit var controller: ResultController
    val resultAdapter = ResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val presenter = ResultPresenterImpl(this)

        val resultDataSourceController = ResultDataSourceImpl()
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
            this@ResultActivity.startActivity(AddMatchDialogActivity.getIntent(this@ResultActivity))
        }
    }

}
