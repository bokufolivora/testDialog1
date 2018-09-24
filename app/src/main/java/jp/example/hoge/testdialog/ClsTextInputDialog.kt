package jp.example.hoge.testdialog

import android.content.Context
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.widget.EditText

// ダイアログ
//   レイアウト(xml)は未使用
class ClsTextInputDialog( mc:Context) {
    // テキスト入力用のダイアログ
    private val mDialog = MyDialog()
    init {
        mDialog.mEdit =  EditText(mc)
    }
    // タイトル
    var dialogTitle : String
        get() {
            return mDialog.mTitle
        }
        set(value) {
            mDialog.mTitle = value
        }
    // メッセージ
    var dialogMessage : String
        get() {
            return mDialog.mMsg
        }
        set(value) {
            mDialog.mMsg = value
        }
    // 入力データ
    var dialogTextData : String
        get() {
            if ( null != mDialog.mEdit) {
                mDialog.mTextData = mDialog.mEdit?.text.toString()
            }
            return mDialog.mTextData
        }
        set(value) {
            mDialog.mTextData = value
        }
    var onOkClickListener : DialogInterface.OnClickListener
        get() {
            // 実際には使用しない
            return DialogInterface.OnClickListener {_, _ -> }
        }
        set(value) {
            mDialog.isOkButton = true
            mDialog.onOkClickListener = value
        }
    var isCancelButton : Boolean
        get() {
            return mDialog.isCancelButton
        }
        set(value) {
            mDialog.isCancelButton = value
        }
    var onCancelClickListener : DialogInterface.OnClickListener
        get() {
            // 実際には使用しない
            return DialogInterface.OnClickListener {_, _ -> }
        }
        set(value) {
            mDialog.isCancelButton = true
            mDialog.onCancelClickListener = value
        }
    fun openDialog(manager: FragmentManager) {
        mDialog.show( manager,"dialog")
    }
    //
    class MyDialog : DialogFragment() {
        var mTitle : String = ""
        var mMsg  : String = ""
        var mEdit : EditText? = null
        var mTextData : String = ""
        var isOkButton : Boolean = false
        var onOkClickListener      : DialogInterface.OnClickListener = DialogInterface.OnClickListener {_, _ -> }
        var isCancelButton : Boolean = false
        var onCancelClickListener : DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ -> }
        //
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // 実際は AlertDialog を使用
            val dialogBuilder = AlertDialog.Builder(activity!!)
            // ここで、設定　
            if (mTitle.isNotEmpty()) {
                dialogBuilder.setTitle(mTitle)
            } else {
                dialogBuilder.setTitle("dialog")
            }
            if (mMsg.isNotEmpty()) {
                dialogBuilder.setMessage(mMsg)
            }
            if (mTextData.isNotEmpty()) {
                mEdit?.setText( mTextData )
                dialogBuilder.setView(mEdit)
            }
            if (isOkButton) {
                dialogBuilder.setPositiveButton(getString(android.R.string.ok), onOkClickListener)
            }
            if (isCancelButton) {
                dialogBuilder.setNegativeButton(getString(android.R.string.cancel), onCancelClickListener)
            }
            return dialogBuilder.create()
        }
        // onPause でダイアログを閉じている
        override fun onPause() {
            super.onPause()
            dismiss()
        }
    }
}