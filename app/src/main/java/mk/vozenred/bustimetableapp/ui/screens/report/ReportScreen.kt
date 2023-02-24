package mk.vozenred.bustimetableapp.ui.screens.report

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.BasicTopAppBar
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.ui.theme.BusTimetableAppTheme
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReportScreen(
  sharedViewModel: SharedViewModel,
  onBackArrowClick: () -> Unit,
) {
  val selectedRelation by sharedViewModel.selectedRelation.collectAsState()
  val context = LocalContext.current
  Scaffold(
    topBar = {
      BasicTopAppBar(title = stringResource(R.string.report_screen_title),
        onBackArrowClick = { onBackArrowClick() }
      )
    },
    content = {
      ReportScreenContent(
        selectedRelation = selectedRelation,
        onReportButtonClick = { selectedRelationId, mailBody ->
          if (mailBody.isNotEmpty()) {
            showEmailChooser(
              relationId = selectedRelation.id,
              mailContentBody = "${selectedRelation}\n\nОбјаснување: \n$mailBody",
              context = context
            )
          } else {
            Toast.makeText(context, "Внесете објаснување за проблемот.", Toast.LENGTH_SHORT).show()
          }
        }
      )
    }
  )
}

@Composable
fun ReportScreenContent(
  selectedRelation: Relation,
  onReportButtonClick: (Int, String) -> Unit
) {

  var userNoteText by remember {
    mutableStateOf("")
  }
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(all = LARGEST_PADDING),
    verticalArrangement = Arrangement.SpaceEvenly
  ) {
    Column(
      modifier = Modifier
        .weight(10f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = LARGEST_PADDING)
      ) {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = selectedRelation.companyName,
          onValueChange = {},
          enabled = false,
          label = {
            Text(text = "Име на компанија:")
          }
        )
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = LARGEST_PADDING),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        TextField(
          value = selectedRelation.startPoint,
          onValueChange = {},
          enabled = false,
          modifier = Modifier.weight(1f),
          label = {
            Text(text = "Од:")
          }
        )
        Icon(
          imageVector = Icons.Filled.ArrowForward,
          contentDescription = stringResource(id = R.string.forward_arrow_icon),
          modifier = Modifier
            .padding(horizontal = ButtonDefaults.IconSize)
        )
        TextField(
          value = selectedRelation.endPoint,
          onValueChange = {},
          enabled = false,
          modifier = Modifier.weight(1f),
          label = {
            Text(text = "До:")
          }
        )
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = LARGEST_PADDING),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        TextField(
          value = selectedRelation.departureTime,
          onValueChange = {},
          enabled = false,
          modifier = Modifier.weight(1f),
          label = {
            Text(text = "Поаѓа:")
          }
        )
        Icon(
          imageVector = Icons.Filled.ArrowForward,
          contentDescription = stringResource(id = R.string.forward_arrow_icon),
          modifier = Modifier
            .padding(horizontal = ButtonDefaults.IconSize)
        )
        TextField(
          value = selectedRelation.arrivalTime,
          onValueChange = {},
          enabled = false,
          modifier = Modifier.weight(1f),
          label = {
            Text(text = "Пристига:")
          }
        )
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = LARGEST_PADDING)
      ) {
        TextField(
          modifier = Modifier.fillMaxWidth(),
          value = selectedRelation.note,
          onValueChange = {},
          enabled = false,
          label = {
            Text(text = "Режим на возење:")
          },
          maxLines = 1
        )
      }
      Row(
        modifier = Modifier
          .padding(bottom = LARGEST_PADDING)
      ) {
        OutlinedTextField(
          modifier = Modifier.fillMaxSize(),
          value = userNoteText,
          onValueChange = { userNoteText = it },
          label = {
            Text(text = "Објаснување:")
          },
          isError = userNoteText.isEmpty(),
        )
      }
    }
    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.Bottom
    ) {
      Button(onClick = {
        onReportButtonClick(selectedRelation.id, userNoteText)
      }, modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.report_screen_title))
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
        Icon(
          imageVector = Icons.Filled.Email,
          contentDescription = stringResource(id = R.string.icon_button_content_description)
        )
      }
    }
  }

}

@Composable
@Preview(name = "Full Preview", showSystemUi = true)
fun ReportScreenContentPreview() {
  BusTimetableAppTheme {
    ReportScreenContent(
      selectedRelation = Relation(
        companyName = "Руле Турс",
        startPoint = "Скопје",
        endPoint = "Куманово",
        departureTime = "10:00",
        arrivalTime = "12:00",
        note = "Секој ден освен во недела."
      ),
      onReportButtonClick = { _, _ -> }
    )
  }
}

fun showEmailChooser(
  relationId: Int,
  mailContentBody: String,
  context: Context
) {
  Log.d("Report Screen", mailContentBody)
  val emailIntent = Intent(Intent.ACTION_SEND)
  emailIntent.type = "message/rfc822"
  emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("info@bustimetable.mk"))
  emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Пријави проблем #${relationId}")
  emailIntent.putExtra(Intent.EXTRA_TEXT, mailContentBody)
  try {
    context.startActivity(Intent.createChooser(emailIntent, "Choose an Email client: "))
  } catch (exception: ActivityNotFoundException) {
    Toast.makeText(context, "Немате апликации за e-mail сервиси.", Toast.LENGTH_SHORT).show()
  }

}