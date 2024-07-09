package com.alexallafi.core.presentation.ui

import com.alexallafi.core.domain.util.DataError
import com.plcoding.core.presentation.ui.R

fun DataError.asUiText(): UIText {
    return when(this) {
        DataError.Local.DISK_FULL -> UIText.StringResource(R.string.error_disk_full)
        DataError.Network.REQUEST_TIMEOUT -> UIText.StringResource(R.string.error_request_timeout)
        DataError.Network.TOO_MANY_REQUESTS -> UIText.StringResource(R.string.error_too_many_requests)
        DataError.Network.NO_INTERNET -> UIText.StringResource(R.string.error_no_internet)
        DataError.Network.PAYLOAD_TOO_LARGE -> UIText.StringResource(R.string.error_payload_too_large)
        DataError.Network.SERVER_ERROR -> UIText.StringResource(R.string.error_server_error)
        DataError.Network.SERIALIZATION -> UIText.StringResource(R.string.error_serialization)
        else -> UIText.StringResource(R.string.error_unknown)
    }
}