package com.utilities.money.livedatanavigation.wizard2

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.utilities.money.livedatanavigation.R
import com.utilities.money.livedatanavigation.navigation.common.Wizards
import com.utilities.money.livedatanavigation.BasicAppActions
import com.utilities.money.livedatanavigation.wizard2.event.Wizard2ChildActions
import com.utilities.money.livedatanavigation.navigation.util.ownActionsScope

class ActivityHost : AppCompatActivity() {

    lateinit var basicAppActions: BasicAppActions

    lateinit var wizard2ChildActions: Wizard2ChildActions

    lateinit var basicAppRouterReferenceText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        this.bindObservers()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard_2_layout)

        this.bindViews()
        this.fillViews()

        this.initWizard()
    }

    override fun onResume() {
        super.onResume()

        this.basicAppActions.changeActionBarTitle("Wizard 2")
    }

    private fun bindObservers() {
        this.basicAppActions = this.ownActionsScope(Wizards.APPLICATION)
        this.basicAppActions.actionBarTitle.observe(this, Observer {
            this.supportActionBar?.title = it
        })

        this.wizard2ChildActions = this.ownActionsScope(Wizards.WIZARD_2)
        this.wizard2ChildActions.sendEvent.observe(this, Observer {
            Toast.makeText(this, "Event Received in ActivityHost", Toast.LENGTH_SHORT).show()
        })
    }

    private fun bindViews() {
        this.basicAppRouterReferenceText = this.findViewById(R.id.references_text)
    }

    private fun fillViews() {
        this.basicAppRouterReferenceText.text = "BasicAppActions: ${basicAppActions}\n" +
                "Wizard2ChildActions: ${wizard2ChildActions}"
    }

    private fun initWizard() {
        val fragment = FragmentChildWizard2()
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.wizard_2_container, fragment)
        transaction.commit()
    }

}