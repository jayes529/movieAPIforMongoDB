# movieAPIforMongoDB
Including a program for importing CSV to MongoDB and the API to check the details



1.ImportCSV.kt
Use the ImportCSV.kt to import the CSV file to MongoDB with specific format(must be same headers with movie.csv).

This program will assign a UUID to each object instead of default object ID from MongoDB.

open_date will be translated as UNIXtimestamp format with UTC timezone.

Just simply enter the file path and indicate your Datebase and Collection name.

The CSV file will be imported to MongoDB.


2.API for connecting MongoDB

This API will use default 8080 port to access MongoDB.

Default Database is "Movie" and the port is 27017, you can change it in application properties.

And the default collections is "movies", you can change it in class "Movie","@Document(collection = "movies")" as well.

This API provides 2 funtions

1.      localhost:8080/movies --> to show all the movie details from the indicated collection

2.      localhost:8080/movieDetails?uuid={uuid} --> to show the specific movie details by the UUID 

