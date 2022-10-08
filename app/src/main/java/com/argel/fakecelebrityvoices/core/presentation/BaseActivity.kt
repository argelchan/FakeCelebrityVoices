package com.argel.fakecelebrityvoices.core.presentation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.argel.fakecelebrityvoices.R
import com.argel.fakecelebrityvoices.core.exception.Failure

abstract class BaseActivity : AppCompatActivity(), OnFailure {

    abstract val fragmentContainer: FragmentContainerView
    val baseNavController by lazy { findNavController(R.id.fragmentContainer) }

    internal fun hideKeyBoard() {
        val view = this.findViewById<View>(android.R.id.content)
        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    internal fun showKeyBoard() {
        val view = this.findViewById<View>(android.R.id.content)
        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(view.windowToken, 0, 0)
        }
    }

    abstract fun setUpNavigation(navController: NavController)

    override fun handleFailure(failure: Failure?) {
        showProgress(false)
    }

    abstract fun showProgress(show: Boolean)
    abstract fun setBinding()
    abstract fun enableBottomNav(enable: Boolean)
    abstract fun enableAppBar(enable: Boolean)

    data class ToolbarContent(
        val title: String = "",
        val titleSize: Int = 20,
        val subtitle: String = "",
        val subtitleSize: Int = 16,
        val menu: Int? = null,
        val navigationIcon: Int? = null,
        val onClickNavigationIcon: () -> Unit = {},
        val onMenuItemClicked: () -> Unit = {}
    )

    abstract fun setToolbarContent(content: ToolbarContent)

}