package me.soushin.sunshine.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.uber.autodispose.AutoDispose
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.base.AutoDisposeFragmentKotlin
import me.soushin.sunshine.ui.base.settings.SettingsAction
import me.soushin.sunshine.ui.base.settings.SettingsStore
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

class SettingsFragment : AutoDisposeFragmentKotlin() {

    @Inject lateinit var settingsAction: SettingsAction
    @Inject lateinit var settingsStore: SettingsStore

    private var editText: EditText by Delegates.notNull()
    private var button: Button by Delegates.notNull()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.setting_fragment, container, false) ?: return null
        editText = view.findViewById<EditText>(R.id.edit_text_zip_code)
        button = view.findViewById<Button>(R.id.button_zip_code)
        button.setOnClickListener {
            settingsAction.updateZipCode(editText.text.toString())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState ?: settingsStore.zipCode()
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(AutoDispose.autoDisposable(this))
                .subscribe {
                    editText.setText(it)
                }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}
