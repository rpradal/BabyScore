package baby.internal.cosmo.fr.babyscore

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ResultActivity : AppCompatActivity(), ResultDisplay {

    lateinit var controller : ResultController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val presenter = ResultPresenterImpl(this)

        val resultDataSourceController = ResultDataSourceImpl()
        val interactor = ResultInteractorImpl(presenter, resultDataSourceController)
        resultDataSourceController.interactor = interactor
        controller = ResultControllerImpl(interactor)
    }

    override fun onResume() {
        super.onResume()
        controller.fetch()
    }

    override fun showResults(results: List<Result>) {
        Toast.makeText(this, results.toString(), Toast.LENGTH_LONG).show()
    }

}
