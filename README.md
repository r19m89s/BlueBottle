# BlueBottle
I constructed this API after misreading an expected interview question. I read it as implementing an API, which returns a list of Blue Bottle locations given a set of them based on a given radius and longitude and latitude of the location itself.
I wrote it using Maven and spring support, using Java 8 and Intellij.
To run, boot "ApplicationService" (given that its the main class), and attempt to interact with the api using the following endpoint format:
/api/cafe_search/fetch.json?cafe_type=all&coordinates=true&latitude={decimal}&longitude={decimal}&radius={integer}
