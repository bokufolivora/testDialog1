package jp.example.hoge.testdialog

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ボタン押されたらダイアログ表示
        btnOpenDialog.setOnClickListener {
            // ダイアログ
            val dialog : ClsTextInputDialog = ClsTextInputDialog(this)
            // 各種設定
            dialog.dialogTitle = "テキスト入力"
            dialog.dialogMessage = "文字を入力してください！"
            dialog.dialogTextData = textView.text.toString()
            dialog.onOkClickListener = DialogInterface.OnClickListener { _,_->
                val textData = dialog.dialogTextData
                textView.text = textData
            }
            dialog.isCancelButton = true
            // 表示
            dialog.openDialog(supportFragmentManager)
        }
    }
}
