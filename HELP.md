# Read Me First

This project is an example of how to use SSE using spring boot.
Later, it should be useful for another projects coming.

# Description of the project

This project is intended as a "mock" project to publish mocked events of locations (further called trajectories). The published event contains the following format: latitude,longitue,altitude and each event is sepparated apart 1 second.
The api provides trajectories for 10 users:
010: 92645 location events
065: 52407 location events
067: 44150 location events
074: 62192 location events
124: 45137 location events
135: 64483 location events
141: 56780 location events
144: 64168 location events
165: 45335 location events
168: 44255 location events
180: 45993 location events

To subscribe to the events, you have to select one of the users and perform a GET to the following url
GET: /trajectories/{userId}

# Run the project
Just do a mvn spring-boot:run
