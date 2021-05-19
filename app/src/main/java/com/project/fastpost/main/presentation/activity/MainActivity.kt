package com.project.fastpost.main.presentation.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.project.fastpost.R
import com.project.fastpost.app.FastPostApp
import com.project.fastpost.global.di.replaceFragment
import com.project.fastpost.global.extension.visible
import com.project.fastpost.main.di.MainScope
import com.project.fastpost.main.presentation.auth.ChooseRoleFragment
import com.project.fastpost.main.presentation.splash.SplashFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import timber.log.Timber


class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MainActivityPresenter {
        getKoin().getScopeOrNull(MainScope.MAIN_ACTIVITY_SCOPE)?.close()
        return getKoin().createScope(
            MainScope.MAIN_ACTIVITY_SCOPE,
            named(MainScope.MAIN_ACTIVITY_SCOPE)
        ).get()
    }

    override fun attachBaseContext(newBase: Context) {
        val newContext: Context?
        Timber.i("SADQQQQFKEF")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val displayMetrics =
                newBase?.resources?.displayMetrics
            val configuration =
                newBase?.resources?.configuration
            if (displayMetrics?.densityDpi != DisplayMetrics.DENSITY_DEVICE_STABLE) {
                // Current density is different from Default Density. Override it
                displayMetrics?.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE
                configuration?.densityDpi = DisplayMetrics.DENSITY_DEVICE_STABLE
                newContext = newBase //baseContext.createConfigurationContext(configuration);
            } else {
                // Same density. Just use same context
                newContext = newBase
            }
        } else {
            // Old API. Screen zoom not supported
            newContext = newBase
        }

        super.attachBaseContext(FastPostApp.localeManager?.setLocale(newContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onFirstInit()

    }

    override fun openSplashFragment() {
        replaceFragment(
            R.id.container,
            SplashFragment.newInstance(),
            SplashFragment.TAG
        )
    }

    override fun openChooseRoleFragment() {
        replaceFragment(
            R.id.container,
            ChooseRoleFragment.newInstance(),
            ChooseRoleFragment.TAG
        )
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLongMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar(show: Boolean) {
        progressBarMain.visible(show)
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        } else {
            val dialog = AlertDialog.Builder(this)
                .setMessage(getString(R.string.out))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    dialog.dismiss()
                    super.onBackPressed()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                )
            )
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                )
            )
        }
    }


}