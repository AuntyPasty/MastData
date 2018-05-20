This is a sample app for Bink

The app parses data from an internal csv file placed in the assets folder.
The data is then formatted and presented in a number of fragments depending on the requirements.

The general idea was to create an app trying to adhere to the MVP architecture. Using RXJava would
have been a bonus.

An app was created from the Android Studio new App wizard. The tabbed activity seemed appropriate.

To start, a basic framework was written with models to define the data objects that would be
passed around.

The opencsv library was used to parse the csv file. A unit test was originally written for this but
did not work as the assetManager needed to be mocked.

A lot of time was spent trying to get the lists displaying in the fragments. There were a few bugs
to be sorted in the UI code. A different presenter was used for each fragment. Originally I
intended to use one. A dialogFragment was used for new Mast submission.

The UI is poor but functional. It has not been tested for robustness. The app meets nearly all
given requirements. The only outstanding issue at the moment is the fragments are not refreshed
after a new entry has been submitted.

Unit tests are JUnit tests using RoboElectric and Mockito. The testing was ok but coverage could
be better. There should be more test cases for boundary conditions.

For time reasons, strings.xml is not usually used. Most text strings are coded where used.

From reading the test description, there are a few areas which may be open to interpretation.
From the given requirements I have made the following assumptions:

- 	When toggling the 5 results between ascending and descending, we are only acting on the same
	five results and not sorting the whole list and getting a new 5 list items.

-	When displaying the total rent, this is referring to the top 5 items only.



