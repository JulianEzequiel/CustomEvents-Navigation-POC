package com.utilities.money.livedatanavigation.wizard1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.utilities.money.livedatanavigation.R
import com.utilities.money.livedatanavigation.navigation.common.Wizards
import com.utilities.money.livedatanavigation.BasicAppEvents
import com.utilities.money.livedatanavigation.wizard1.event.Wizard1ChildEvents
import com.utilities.money.livedatanavigation.navigation.util.getObserver

class FragmentChildWizard1 : Fragment() {

    lateinit var ownEvents: Wizard1ChildEvents

    lateinit var basicAppRouterReferenceText : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_child_wizard_1_layout, container, false)

        this.bindViewListeners(view)

        this.bindViews(view)
        this.fillViews()

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        this.ownEvents = this.getObserver(Wizards.WIZARD_1)
    }

    private fun bindViewListeners(view: View) {
        view.findViewById<Button>(R.id.show_event_button)?.setOnClickListener {
            this.ownEvents.sendEvent.call()
        }
    }

    private fun bindViews(view: View) {
        this.basicAppRouterReferenceText = view.findViewById(R.id.references_text)
    }

    private fun fillViews() {
        this.basicAppRouterReferenceText.text = "Wizard1ChildEvents: ${ownEvents}"
    }

}