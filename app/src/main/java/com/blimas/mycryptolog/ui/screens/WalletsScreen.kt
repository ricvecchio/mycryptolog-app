package com.blimas.mycryptolog.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blimas.mycryptolog.model.ProcessedHolding
import com.blimas.mycryptolog.model.Transaction
import com.blimas.mycryptolog.model.Wallet
import com.blimas.mycryptolog.ui.components.WalletCard

@Composable
fun WalletsScreen(wallets: List<Wallet>, allTransactions: List<Transaction>) {
    val processedWallets = wallets.map { wallet ->
        val holdings = wallet.cryptoHoldings.mapNotNull { (crypto, currentQuantity) ->
            if (currentQuantity > 0) {
                val relevantTransactions = allTransactions.filter { it.walletId == wallet.id && it.crypto == crypto }
                val relevantBuys = relevantTransactions.filter { it.type.equals("BUY", ignoreCase = true) }

                val totalCostOfBuys = relevantBuys.sumOf { it.price * it.quantity }
                val totalQuantityBought = relevantBuys.sumOf { it.quantity }
                val totalProceedsFromSells = relevantTransactions.filter { it.type.equals("SELL", ignoreCase = true) }.sumOf { it.price * it.quantity }

                ProcessedHolding(
                    crypto = crypto,
                    currentQuantity = currentQuantity,
                    netInvestedValue = totalCostOfBuys - totalProceedsFromSells,
                    avgBuyPrice = if (totalQuantityBought > 0) totalCostOfBuys / totalQuantityBought else 0.0
                )
            } else null
        }
        wallet to holdings
    }

    WalletsContent(processedWallets = processedWallets)
}

@Composable
private fun WalletsContent(processedWallets: List<Pair<Wallet, List<ProcessedHolding>>>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 88.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(processedWallets) { (wallet, holdings) ->
            WalletCard(wallet = wallet, holdings = holdings)
        }
    }
}
