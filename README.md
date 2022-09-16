# BusTimetableApp

An Android Application for intercity bus relations throughout Macedonia.
The app uses a public Excel document from "Ministry of Transport & Communications of Macedonia".

1. The data from the excel document is gathered and converted using [this repo](https://github.com/viktorilievski/Bus-Timetable-Excel-To-Json).
2. The JSON file is stored on Google Firestore as a cloud platform and it is fetched only at the start of the app, then stored in local Room database.
3. Versioning is made on the remote and the local database and it is checked whether the versions are the same on the start of the app. If they are, then we proceed without fetching new data from the server. In other case, the data from the database is rewritten with the new data from the server.
4. Each time a search is made, the data is retrieved from the local Room database - therefore it performs very fast and efficient.
