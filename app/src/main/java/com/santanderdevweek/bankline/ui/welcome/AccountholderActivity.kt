package com.santanderdevweek.bankline.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.santanderdevweek.bankline.data.BanklineApi
import com.santanderdevweek.bankline.data.Currency
import com.santanderdevweek.bankline.data.NetworkUtils
import com.santanderdevweek.bankline.databinding.ActivityAccountholderBinding
import com.santanderdevweek.bankline.domain.AccountItem
import com.santanderdevweek.bankline.domain.Correntista
import com.santanderdevweek.bankline.extensions.formatCurrency
import com.santanderdevweek.bankline.ui.statement.BankStatementActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class AccountholderActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAccountholderBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(BankStatementActivity.EXTRA_ACCOUNT_HOLDER)
            ?: throw IllegalArgumentException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getAccount()

        binding.tvVerExtrato.setOnClickListener {
            val intent = Intent(this, BankStatementActivity::class.java).apply {
                putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder)
            }
            startActivity(intent)
        }
    }
    fun getAccount() {
        val retrofitClient = NetworkUtils.getRetrofitInstance()
        val endPoint = retrofitClient.create(BanklineApi::class.java)
        val callback = endPoint.findAccountsHolder()

        callback.enqueue(object : Callback<List<AccountItem>> {
            override fun onFailure(Call: Call<List<AccountItem>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override  fun onResponse(call: Call<List<AccountItem>>, response: Response<List<AccountItem>>){
                response.body()?.forEach {
                    binding.tvUsuario.text = "${"Olá "}" + it.nome
                    binding.tvAgencia.text = "${"Conta: "}" + it.conta.numero
                    val value = it.conta.saldo.toString()
                    val currency = Currency.getByName(value)
                    binding.tvSaldo.text = it.conta.saldo.toDouble().formatCurrency(currency.locale)
                }
            }
        } )
    }
}

