package com.santanderdevweek.bankline.ui.statement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santanderdevweek.bankline.R
import com.santanderdevweek.bankline.data.Currency
import com.santanderdevweek.bankline.databinding.BankStatementItemBinding
import com.santanderdevweek.bankline.domain.Movimentacao
import com.santanderdevweek.bankline.domain.TipoMovimentacao
import com.santanderdevweek.bankline.extensions.formatCurrency
import java.text.SimpleDateFormat
import java.util.*


class BankStatementAdapter(private val dataSet: List<Movimentacao>) : RecyclerView.Adapter<BankStatementAdapter.ViewHolder>() {

    class ViewHolder(private val binding: BankStatementItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movimentacao) = with(binding) {
            tvDescription.text = item.descricao
            val value = tvValue.text.toString()
            val currency = Currency.getByName(value)
            tvValue.text = item.valor.formatCurrency(currency.locale)
            val formatstorege = SimpleDateFormat("yyyy-MM-dd")
            val originaldate: Date = formatstorege.parse(item.dataHora)!!
            val date = SimpleDateFormat("dd/MM/yyyy")
            tvDatetime.text = date.format(originaldate)
            val typeIcon = if (TipoMovimentacao.RECEITA == item.tipo) R.drawable.ic_money_on
            else R.drawable.ic_money_out
            ivIcon.setImageResource(typeIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BankStatementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = dataSet.size
}