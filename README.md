# BusTimetableApp

An Android Application for intercity bus relations throughout Macedonia.
The app uses a public Excel document from "Ministry of Transport & Communications of Macedonia".

## Technologies, libraries and frameworks used until now:
- Jetpack Compose
- Room Database
- Kotlin Coroutines
- DataStore Preferences
- Google Firebase/Firestore
- MVVM and Repository Pattern
- One Activity multiple Screens pattern
- Navigation throughout the app using `NavHost` and `NavHostController` (animated)
- Android Hilt


## Information about the app and how it works:
1. The data from the excel document is gathered and converted using [this repo](https://github.com/viktorilievski/Bus-Timetable-Excel-To-Json).
2. The **JSON file** is stored on Google Firestore as a cloud platform and it is fetched **only** at the start of the app, then stored in local Room database.
3. Versioning is made on the remote and the local database and it is checked whether the versions are the same on the start of the app. If they are, then we proceed without fetching new data from the server. In other case, the data from the database is rewritten with the new data from the server.
4. Each time a search is made, the data is retrieved from the local Room database - therefore it performs very fast and efficient.
5. End Point/Arrival destination depends and it's calculated based on the Start Point/Departure choice of destination. If there are no relations "A -> B" then the "B" destination is not present on the screen. This is done by a simple SQL query.
6. A feature about 'Live Relations' is present in the app - meaning that whenever it's triggered it will only show the relations from the current time onwards. Also made available with an SQL query.
7. The state of the 'Live Relation' toggle is stored in DataStore, so the choice of the user is saved even after restarting the app.
8. The user of the app can report a relation if/whether the information about it is incorrect or misleading. It is being done by auto-completing an e-mail with the data and information provided.
9. There is an option to store a favorite relation so the user can easily check out his favorite relations using the Drawer.
10. The user can also sort the starting/ending destinations alphabetically or by popularity of the relations
11. They can also be searched from the Top Bar and the search is not case sensitive (only with Macedonian keyboard)
