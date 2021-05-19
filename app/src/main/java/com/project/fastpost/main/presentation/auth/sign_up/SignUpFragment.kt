package com.project.fastpost.main.presentation.auth.sign_up

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.project.fastpost.R
import com.project.fastpost.global.base.PhotoBaseFragment
import com.project.fastpost.global.base.pickPhotoFromCameraWithPermissionCheck
import com.project.fastpost.global.base.pickPhotoFromGalleryWithPermissionCheck
import com.project.fastpost.global.di.replaceFragment
import com.project.fastpost.global.extension.visible
import com.project.fastpost.main.di.MainScope
import com.project.fastpost.main.presentation.customer.main.MainCustomerFragment
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import java.util.*

class SignUpFragment : PhotoBaseFragment(), SignUpView {

    companion object {

        val TAG = "SignUpFragment"

        fun newInstance(): SignUpFragment =
            SignUpFragment()
    }

    @InjectPresenter
    lateinit var presenter: SignUpPresenter

    @ProvidePresenter
    fun providePresenter(): SignUpPresenter {
        getKoin().getScopeOrNull(MainScope.SIGN_UP_SCOPE)?.close()
        return getKoin().createScope(MainScope.SIGN_UP_SCOPE, named(MainScope.SIGN_UP_SCOPE)).get()
    }

    override fun setImage(uri: Uri) {
        presenter.onSetImage(uri)
    }

    override fun setImagePath(path: String) {
        presenter.onSetImagePath(path)
    }

    override fun setVideo(uri: Uri) {

    }

    override fun setVideoPath(path: String) {

    }


    override val layoutRes: Int
        get() = R.layout.fragment_registration

    private var pin: String = ""

    override fun setUp(savedInstanceState: Bundle?) {

        btnRegistration.setOnClickListener {

            presenter.onNextBtnClicked(
                name = edtNameRegistration.text.toString(),
                surname = edtSurnameRegistration.text.toString(),
                phone = "8" + edtPhoneRegistration.unMasked,
                password = edtPasswordRegistration.text.toString()
            )

            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            val layoutInflater: LayoutInflater = LayoutInflater.from(context)
            val view: View = layoutInflater.inflate(R.layout.fragment_sign_up_sms, null, false)

            val code = view.findViewById(R.id.pinSUS) as PinEntryEditText
            code.setOnPinEnteredListener { pin = it.toString() }

            val dialog: AlertDialog? = builder.setView(view)
                .setTitle("Код")
                .setNegativeButton("Изменить номер") { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton("Ok") { _: DialogInterface, i: Int ->

                    presenter.onRegistrationBtnClicked(
                        name = edtNameRegistration.text.toString(),
                        surname = edtSurnameRegistration.text.toString(),
                        phone = "8" + edtPhoneRegistration.unMasked,
                        password = edtPasswordRegistration.text.toString(),
                        code = pin
                    )
                }.create()
            dialog?.setOnShowListener {
                dialog?.getButton(AlertDialog.BUTTON_NEGATIVE)
                    ?.setTextColor(resources.getColor(R.color.colorPink))
                dialog?.getButton(AlertDialog.BUTTON_POSITIVE)
                    ?.setTextColor(resources.getColor(R.color.colorPink))
            }
            dialog?.setCancelable(false)
            dialog?.setCanceledOnTouchOutside(false)

            dialog?.show()
        }

        imgUdLichDriverConfirm?.setOnClickListener { uploadImageId() }
        imgUdLichBackDriverConfirm?.setOnClickListener { uploadImageIdBack() }
        txtDateRegistration.setOnClickListener { showDataPickerDialog() }
    }

    private fun showDataPickerDialog() {
        val calendar = Calendar.getInstance()
        context?.let {
            val dialog = DatePickerDialog(
                it,
                dateListener,
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            )
            dialog.show()
            dialog.getButton(TimePickerDialog.BUTTON_POSITIVE)?.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.colorPink
                )
            )
            dialog.getButton(TimePickerDialog.BUTTON_NEGATIVE)?.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.colorPink
                )
            )
        }
    }

    private val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        presenter.onDateSelected(year, month + 1, dayOfMonth)
    }

    override fun showDateTitle(title: String) {
        txtDateRegistration?.text = title
    }


    override fun uploadImageId() {
        AlertDialog.Builder(context)
            .setItems(arrayOf("Сделать снимок", "Выбрать из галереи")) { _, which ->
                when (which) {
                    0 -> presenter.onIdentityBtnClicked()
                    1 -> presenter.onIdentityBtnClickedGallery()
                }
            }.show()
    }

    override fun uploadImageIdBack() {
        AlertDialog.Builder(context)
            .setItems(arrayOf("Сделать снимок", "Выбрать из галереи")) { _, which ->
                when (which) {
                    0 -> presenter.onIdentityBackBtnClicked()
                    1 -> presenter.onIdentityBackBtnClickedGallery()
                }
            }.show()
    }

    override fun openGallery() {
        pickPhotoFromGalleryWithPermissionCheck()
    }

    override fun openCamera() {
        pickPhotoFromCameraWithPermissionCheck()
    }

    override fun showIdentityImage(bitmap: Bitmap) {
        activity?.runOnUiThread {
            imgUdLichDriverConfirm?.setImageBitmap(bitmap)
            imgConfirmPassport.visible(false)
        }
    }

    override fun showIdentityImageBack(bitmap: Bitmap) {
        activity?.runOnUiThread {
            imgUdLichBackDriverConfirm?.setImageBitmap(bitmap)
            imgConfirmPassport2.visible(false)
        }
    }

    private fun openHomeCustomerFragment() {
        activity?.replaceFragment(
            R.id.container,
            MainCustomerFragment.newInstance(),
            MainCustomerFragment.TAG
        )
    }

    override fun openDialogSuccess() {

        val dialog = AlertDialog.Builder(context)
            .setTitle("Поздравляем!")
            .setMessage("Вы успешно зарегестрированы!")
            .setPositiveButton("ПРОДОЛЖИТЬ") { _: DialogInterface, i: Int ->
                openHomeCustomerFragment()
            }.create()
        dialog?.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                ?.setTextColor(resources.getColor(R.color.colorPink))
        }
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)

        dialog?.show()
    }

}