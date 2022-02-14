# FoodLover
This project was made in Kotlin using AndroidX and the following libs (the major libs are in bold):
- **Koin**
- **Coroutines**
- **Navigation**
- **Databinding**
- **Room**
- Gson
- SwipeRefreshLayout
- ConstraintLayout
- Cardview
- **Espresso**
- **Mockk**

## Functionality
This is a single-activity app that reads a restaurant list from a Json file and provides the following functionalities:
- From the restaurant list have the ability to favorite and unfavorite a restaurant.
- From the restaurant list have the ability to sort the restaurant list based on the restaurants current opening state.
- From the restaurant list have the ability to sort the restaurant list further with additional sorting options.
- From the restaurant list have the ability to use **swipe to refresh in order to update the current shown list sort.**.
- From the restaurant details page have the ability to see the restaurant information (name, status, sorting values, favorite state).

## Important Points
- The restaurant structure in the provided restaurant list does not have a primary key to use as id or to combine in order to create one, so the name was used for this purpose.
- If the Restaurant response status, does not corresponds to any of the mapped domain status then the instance will be removed in the mapping process.
- The json list was treated as a mocked api result, and the app architecture was built taking it into account.
- The filtered list has been sorted in different ways depending on the selected filter option, as follows:
    - BEST_MATCH -> Descending
    - NEWEST -> Ascending
    - RATING_AVERAGE -> Descending
    - DISTANCE -> Ascending
    - POPULARITY -> Descending
    - AVERAGE_PRODUCT_PRICE -> Ascending
    - DELIVERY_COSTS -> Ascending
    - MIN_COST -> Ascending

## Future improvements
- We have unit & instrumented tests, but it would be good to have Integration tests as well.
- Empty state for error scenarios, when it's not possible to show the result list.
- The list animation in the ListAdapter has a bug, it always keeps the top item presented in the list at the top, what gives the necessity to scroll top sometimes after applying a new filter.
- The navigation allows double tap.

## Architecture Notes
- This was built to be maintained for a while.
- This is a multi module project, focused on the Clean Architecture layers.
- Domain is divided in two modules, **impl** and **public**. Where the impl has the final classes and the public has the exposed abstractions.
- The architecture used was MVVM, with UseCases, DataSources & Repositories (Clean Architecture).
- The code design and style was intended to be consistent and reasonable.

Please ask if you have any questions!
