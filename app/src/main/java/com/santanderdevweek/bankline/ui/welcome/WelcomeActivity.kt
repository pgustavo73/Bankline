package com.santanderdevweek.bankline.ui.welcome

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.santanderdevweek.bankline.databinding.ActivityWelcomeBinding
import com.santanderdevweek.bankline.domain.Correntista
import com.santanderdevweek.bankline.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btContinue.setOnClickListener {
            if (TextUtils.isEmpty(binding.etAccountHolderId.text.toString())) {
                showToast("Empty field not allowed!")
            } else {
                val accountHolderId = binding.etAccountHolderId.text.toString().toInt()
                val accountHolder = Correntista(accountHolderId)
                val intent = Intent(this, AccountholderActivity::class.java).apply {
                    putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder)
                }
                startActivity(intent)
            }
        }
    }

}