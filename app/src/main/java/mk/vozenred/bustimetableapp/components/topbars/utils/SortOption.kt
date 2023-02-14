package mk.vozenred.bustimetableapp.components.topbars.utils

enum class SortOption(val description: String) {
  ALPHABETICAL(description = "Азбучен редослед растечки"),
  ALPHABETICAL_INVERTED(description = "Азбучен редослед опаѓачки"),
  MAX_RELATIONS(description = "Популарност растечки"),
  MIN_RELATIONS(description = "Популарност опаѓачки")
}