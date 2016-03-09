# REST-With-JAX-RS-Person

Username is person, password is test, role is persontest.
POST, PUT and DELETE should be authenticated, but for some reason it will not accept jack shit, and I already have working authentication on the Quote part of this assignment and it should work the exact same way, and it doesn't, and with the time constraints we have on this stuff I've just given up on that now.
POST works the same as in the Quote assignment. Body with a JSON object, set up like { "fName":"Baby","lName":"Dave","phone":"42042069","id":"187" }
PUT isn't implemented in this assignment.
DELETE should work in the same way as the Quote assignment, just send a delete request to api/persons/{number}.

The username and password are also included in the unit tests for basic authentication. But auth doesn't work, so oh well.
