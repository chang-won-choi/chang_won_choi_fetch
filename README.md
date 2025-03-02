# Fetch Hiring App

A simple **native Android app** (in Java) that retrieves data from [Fetch’s Hiring API](https://fetch-hiring.s3.amazonaws.com/hiring.json) and displays the results in a scrollable list.

## Features
1. **Displays data grouped by `listId`.**
   - Internally sorted first by `listId`, then by `name`.
2. **Filters** out items where `name` is null or blank.
3. **Sort** the results first by "listId" then by "name" when displaying.

## Requirements
- **Android Studio**
- **JDK 8** or higher
- **Min SDK**: 21

