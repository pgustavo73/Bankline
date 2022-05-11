package com.santanderdevweek.bankline.ui.statement

import androidx.lifecycle.ViewModel
import com.santanderdevweek.bankline.data.BanlLineRepository

class BankStatementViewModel : ViewModel() {

    fun findBankStatement(accountHolderId: Int) =
        BanlLineRepository.findBankStatement(accountHolderId)
}