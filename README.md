# FishingLog

The application aims to eliminate the need for filling out a paper version of the fishing log for each fishing session and facilitate the collection of data from the sessions.

Each year in Poland, approximately 600,000 anglers fill out such logs.

To process the data, assuming an optimistic scenario that one log takes 5 minutes to complete, it would require 50,000 man-hours to enter this data into an Excel spreadsheet. With around 2,500 fishing clubs in Poland, this translates to 20 man-hours per club.

<img src="https://i.imgur.com/jlBH2sz.png" alt="rejestr połowu ryb">

### Technologies used:
- Spring Boot
- Spring Data JPA
- Spring Web MVC
- Spring Security
- Hibernate
- MySQL
- HTML/CSS/JavaScript

### User view


![user](https://github.com/KrzysztofGarus/FishingApp/assets/117105005/b79b8260-5cfd-4537-9a72-c6e7846de7c0)


### Admin view

![admin](https://github.com/KrzysztofGarus/FishingApp/assets/117105005/ac072298-9997-4722-9777-b4408542c412)



### User features:
- Dashboard, providing a starting view with statistics for the user and a calendar of fishing sessions.
- Ability to add / edit / delete fishing sessions.
- Ability to add / edit / delete fish within a specific fishing session.
- Map view displaying the locations of fishing spots.

### Admin features:
- Dashboard, presenting statistics and a calendar for all users and fishing sessions.
- Displaying a list of fishing spots.
- Ability to add / edit / delete fishing spots.
- Displaying details of a fishing spot - statistics for a specific fishing spot.
- Displaying a list of fish.
- Ability to add / edit / delete fish names.
