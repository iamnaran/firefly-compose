package com.iamnaran.firefly.ui.main.notification.ble.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.ui.main.notification.ble.dto.BleDeviceEntity
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.ui.theme.dimens

@Composable
fun DeviceInfoItem(bleDeviceEntity: BleDeviceEntity, onConnectClick: (BleDeviceEntity) -> Unit) {

    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.medium)
            .shadow(
                elevation = 5.dp,
                spotColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
        ) {

            Column(
                Modifier
                    .padding(10.dp),
            ) {
                Text(
                    text = bleDeviceEntity.deviceName,
                    style = appTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

            }

            Button(onClick = {
                onConnectClick(bleDeviceEntity)
            }) {
                Text(text = "Connect")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireflyComposeTheme {
        DeviceInfoItem(BleDeviceEntity("Name")) {

        }
    }
}