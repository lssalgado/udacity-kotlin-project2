# Asteroid Radar

## How to setup your API Key
Open `local.properties` located at project's root and add a variable named `NASA_API_KEY` with your key like so:

`NASA_API_KEY=THATSNOTAVALIDAPIKEY`

After that all you need to do is build and run the project :)

## Checklist

- [x] Setup the NasaApi;
- [x] Add support to non-versioned API Key;
- [x] Filter and show the image of the day;
- [x] Use `PictureOfDay` instead of `ImageOfTheDay`;
- [x] Build the recycler view of the home fragment;
- [x] Setup a database;
- [x] Fix WHERE to use dynamic date;
- [x] The app can save the downloaded asteroids in the database and then display them also from the database;
- [x] The app filters asteroids from the past;
- [x] The app downloads the next 7 days asteroids and saves them in the database once a day using workManager with requirements of internet connection and device plugged in. The app can display saved asteroids from the database even if internet connection is not available;
- [x] The app works correctly in talk back mode, it provides descriptions for all the texts and images: Asteroid images in details screen and image of the day. It also provides description for the details screen help button;
- [x] The app displays a list of asteroids in the Main Screen by using a RecyclerView, when tapping an item the app opens Details screen;
- [ ] Modify the app to support:
    - [ ] Multiple languages;
    - [x] Device sizes;
    - [x] Orientations.
- [x] The asteroids displayed in the screens are downloaded from the NASA API;
- [x] The NASA image of the day is displayed in the Main Screen;
- [x] Make the app delete asteroids before today once a day from the same workManager that downloads the asteroids;
- [x] Provide styles for the details screen subtitles and values to make it consistent, and make it look like in the designs;
- [x] Make sure the entire app works without an internet connection;
- [ ] Improve try/catch in MainViewModel;
- [x] Test Workers;
- [ ] Support Right to Left languages;
- [ ] Expand the checklist with all the items from Rubric.