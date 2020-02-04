package com.utilities.money.livedatanavigation.wizard2

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.utilities.money.livedatanavigation.R
import com.utilities.money.livedatanavigation.navigation.common.Wizards
import com.utilities.money.livedatanavigation.BasicAppEvents
import com.utilities.money.livedatanavigation.wizard2.event.Wizard2ChildEvents
import com.utilities.money.livedatanavigation.navigation.util.getObserver
import com.utilities.money.livedatanavigation.navigation.util.ownEvents

class ActivityHost : AppCompatActivity() {

    lateinit var basicAppEvents: BasicAppEvents

    lateinit var wizard2ChildEvents: Wizard2ChildEvents

    lateinit var basicAppRouterReferenceText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard_2_layout)

        this.bindObservers()

        this.bindViews()
        this.fillViews()

        this.initWizard()
    }

    override fun onResume() {
        super.onResume()

        this.basicAppEvents.changeActionBarTitle("Wizard 2")
    }

    private fun bindObservers() {
        this.basicAppEvents = this.ownEvents(Wizards.APPLICATION)
        this.basicAppEvents.actionBarTitle.observe(this, Observer {
            this.supportActionBar?.title = it
        })

        this.wizard2ChildEvents = this.ownEvents(Wizards.WIZARD_2)
        this.wizard2ChildEvents.sendEvent.observe(this, Observer {
            Toast.makeText(this, "Event Received in ActivityHost", Toast.LENGTH_SHORT).show()
        })
    }

    private fun bindViews() {
        this.basicAppRouterReferenceText = this.findViewById(R.id.references_text)
    }

    private fun fillViews() {
        this.basicAppRouterReferenceText.text = "BasicAppEvents: ${basicAppEvents}\n" +
                "Wizard2ChildEvents: ${wizard2ChildEvents}"
    }

    private fun initWizard() {
        val fragment = FragmentChildWizard2()
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.wizard_2_container, fragment)
        transaction.commit()
    }

}