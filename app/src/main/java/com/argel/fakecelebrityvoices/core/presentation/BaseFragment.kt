package com.argel.fakecelebrityvoices.core.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.argel.fakecelebrityvoices.R
import com.argel.fakecelebrityvoices.core.exception.Failure
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), OnFailure {

    val navController by lazy { findNavController() }
    val baseActivity by lazy { requireActivity() as BaseActivity }

    open fun useBottomNav() = false
    open fun useAppBar() = false
    open fun setToolbar() = BaseActivity.ToolbarContent()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.apply {
            setUpNavigation(navController)
            val useAppBar = useAppBar()
            enableAppBar(useAppBar)
            if (useAppBar) setToolbarContent(setToolbar())
            enableBottomNav(useBottomNav())
        }

        setStatusBarColor()

        setBinding(view)
    }

    abstract fun setBinding(view: View)

    fun setStatusBarColor(color: Int = R.color.blue) {
        baseActivity.window.statusBarColor = resources.getColor(color, null)
    }

    open fun onViewStateChanged(state: BaseViewState?) {
        when (state) {
            BaseViewState.ShowLoading -> showLoader(true)
            BaseViewState.HideLoading -> showLoader(false)
            else -> {
            }
        }
    }

    val focusListener =
        View.OnFocusChangeListener { _, hasFocus -> if (!hasFocus) hideKeyboard() else showKeyboard() }

    fun hideKeyboard() = baseActivity.hideKeyBoard()

    private fun showKeyboard() = baseActivity.showKeyBoard()

    open fun showLoader(show: Boolean) = baseActivity.showProgress(show)

    inline fun <reified T : ViewModel> Fragment.hiltNavGraphViewModels(@IdRes navGraphIdRes: Int) =
        viewModels<T>(
            ownerProducer = { findNavController().getBackStackEntry(navGraphIdRes) },
            factoryProducer = { defaultViewModelProviderFactory }
        )

    private fun showToast(message: String) = Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()

    override fun handleFailure(failure: Failure?) {
        showLoader(false)
        when (failure) {
            is Failure.ServerError -> showToast(failure.serverMessage ?: "")
            is Failure.NetworkConnection -> {
                showToast(getString(failure.defaultMessage))
            }
            is Failure.FeatureFailure -> showToast(getString(failure.defaultMessage))
        }
    }

}