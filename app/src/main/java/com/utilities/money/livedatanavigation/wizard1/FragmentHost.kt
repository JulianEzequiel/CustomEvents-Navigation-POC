package com.utilities.money.livedatanavigation.wizard1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.utilities.money.livedatanavigation.R
import com.utilities.money.livedatanavigation.navigation.common.Wizards
import com.utilities.money.livedatanavigation.BasicAppActions
import com.utilities.money.livedatanavigation.navigation.util.getScopedActions
import com.utilities.money.livedatanavigation.navigation.util.ownActionsScope
import com.utilities.money.livedatanavigation.wizard1.event.Wizard1ChildActions

class FragmentHost : Fragment() {

    lateinit var basicAppActions: BasicAppActions

    lateinit var wizard1ChildActions: Wizard1ChildActions

    lateinit var basicAppRouterReferenceText : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wizard_1_host_layout, container, false)

        this.bindObservers()

        this.bindViews(view)
        this.fillViews()

        this.initWizard()

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        this.wizard1ChildActions = this.ownActionsScope(Wizards.WIZARD_1)
        this.basicAppActions = this.getScopedActions(Wizards.APPLICATION)
    }

    override fun onResume() {
        super.onResume()

        this.basicAppActions.changeActionBarTitle("Wizard 1")
    }

    private fun bindObservers() {
        this.wizard1ChildActions.sendEvent.observe(this, Observer {
            Toast.makeText(this.context, "Event Received in FragmentHost", Toast.LENGTH_SHORT)
                .show()
        })
    }

    private fun bindViews(view: View) {
        this.basicAppRouterReferenceText = view.findViewById(R.id.references_text)
    }

    private fun fillViews() {
        this.basicAppRouterReferenceText.text = "basicAppActions: ${basicAppActions}\n" +
                "Wizard1ChildActions: ${wizard1ChildActions}"
    }

    private fun initWizard() {
        val fragment = FragmentChildWizard1()
        val transaction = this.childFragmentManager.beginTransaction()
        transaction.replace(R.id.wizard_1_container, fragment)
        transaction.commit()
    }


}