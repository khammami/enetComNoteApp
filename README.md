# NoteApp
In this practical, you build an app that uses the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html). The app, called NoteApp, stores a list of notes in a Room database and displays the list in a RecyclerView. The NoteApp app is basic, but sufficiently complete that you can use it as a template to build on.

The NoteApp app does the following:

* Works with a database to get and save notes
* Displays all the notes in a RecyclerView in MainActivity.
* Opens a second Activity when the user taps the + FAB button. When the user enters a note, the app adds the note to the database and then the list updates automatically.
* Opens the same second Activity when user taps a note in the RecyclerView to edit it.
* On the second Activity , the user may choose a specific date for the note. If not, the note take the current date.
* Do not initializes the data in the database if there is no existing data.
* Add a menu item that allows the user to delete all the data.
* You also enable the user to swipe a note to delete it from the database.

## Application overview

<img width="200" src="./assets/f5cc340a4ea5cc11.png"> <img width="200" src="./assets/39d7bea764cc4a18.png"> <img width="200" src="./assets/d471316b6021ef76.png">

<img width="200" src="./assets/e6755adf6aaf8b1b.png"> <img width="200" src="./assets/39d7bea764cc4a18.png"> <img width="200" src="./assets/5ed9221134433d53.png">

<img width="200" src="./assets/e040dec44838c86e.png"> <img width="200" src="./assets/7762c8fdf68cc252.png"> <img width="200" src="./assets/b4cf57c934657c45.png">
 
**Note**: Confirmation Alerts are optionals

You must follow the diagram below for your database table

![note_table](./assets/49ffb103aa255e58.png)

## IMPORTANT
in order to store complex data like Date in RoomDatabase you need to use converters:
```java
public class Converters {
   @TypeConverter
   public static Date fromTimestamp(Long value) {
       return value == null ? null : new Date(value);
   }

   @TypeConverter
   public static Long dateToTimestamp(Date date) {
       return date == null ? null : date.getTime();
   }
}
```
 
then add the following annotation to your RoomDatabase class under @Database :
 ```java
@Database(entities = ...)
@TypeConverters({Converters.class})
```
