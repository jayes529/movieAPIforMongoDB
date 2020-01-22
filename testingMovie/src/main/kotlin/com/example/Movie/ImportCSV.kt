

import com.mongodb.MongoClient
import com.mongodb.client.model.Filters
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Paths
import java.nio.file.Files
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import org.bson.Document
import javax.swing.JOptionPane.*

fun timezoneAndTimestamp(date: String): Long {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC")) //change time zone

    val dateformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    val unixtime = dateformat.parse(date).time


    return unixtime

}



    fun main() {

        val CSV_File_Path = showInputDialog(null, "Pls input filepath for importing")

        var dbName = showInputDialog(null, "Pls input Database name")

        var collectionName= showInputDialog(null, "Pls input Collection name")

        var objectid: String = UUID.randomUUID().toString()

        var counter: Int =0

         val mongoClient = MongoClient("localhost", 27017)
         val mongoDatabase = mongoClient.getDatabase(dbName)
         val collection = mongoDatabase.getCollection(collectionName)
        try {
         // check uuid whether duplicated or not
          fun checkDupId(): String {

            var i = objectid

            if(collection.countDocuments(Filters.eq("_id", objectid)).toInt() > 0) {

                i = UUID.randomUUID().toString()
            }

            return i
        }


         val reader = Files.newBufferedReader(Paths.get("$CSV_File_Path"))

         val csvParser = CSVParser(
             reader, CSVFormat.DEFAULT
                 .withFirstRecordAsHeader()
                 .withIgnoreHeaderCase()
                 .withTrim()
         )

         for (csvRecord in csvParser) {


             // Accessing values by the names assigned to each column
             val _id = checkDupId()
             val chi_name = csvRecord.get("chi_name")
             val eng_name = csvRecord.get("eng_name")
             val chi_genre = csvRecord.get("chi_genre")
             val eng_genre = csvRecord.get("eng_genre")
             val chi_director = csvRecord.get("chi_director")
             val eng_director = csvRecord.get("eng_director")
             val chi_cast = csvRecord.get("chi_cast")
             val eng_cast = csvRecord.get("eng_cast")
             val open_date = timezoneAndTimestamp(csvRecord.get("open_date"))
             val thumbnail = csvRecord.get("thumbnail")
             val chi_synopsis = csvRecord.get("chi_synopsis")
             val eng_synopsis = csvRecord.get("eng_synopsis")
             val category = csvRecord.get("category")
             val duration = csvRecord.get("duration")
             val document = Document(
                 "_id", "$_id"
             )
                 .append("chi_name", "$chi_name")
                 .append("eng_name", "$eng_name")
                 .append("chi_genre", "$chi_genre")
                 .append("eng_genre", "$eng_genre")
                 .append("chi_director", "$chi_director")
                 .append("eng_director", "$eng_director")
                 .append("chi_cast", "$chi_cast")
                 .append("eng_cast", "$eng_cast")
                 .append("open_date", open_date)
                 .append("thumbnail", "$thumbnail")
                 .append("chi_synopsis", "$chi_synopsis")
                 .append("eng_synopsis", "$eng_synopsis")
                 .append("category", "$category")
                 .append("duration", duration)

             val list = ArrayList<Document>()
             list.add(document)
             collection.insertMany(list)
             counter++
         }
     } catch (e: Exception) {
         System.err.println(e.javaClass.name + ": " + e.message)
     }

        println("Imported " + counter + " objects to DB: $dbName, collection: $collectionName" )
        println("Your CSV file has been imported")

    }





