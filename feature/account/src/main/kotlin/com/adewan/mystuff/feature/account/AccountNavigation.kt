package com.adewan.mystuff.feature.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val accountRoute = "account_route"

fun NavController.navigateToAccountView() {
    this.navigate(accountRoute)
}

fun NavGraphBuilder.accountView(showBottomBar: (Boolean) -> Unit) {
    composable(route = accountRoute) {
        showBottomBar(false)
        AccountView()
    }
}
