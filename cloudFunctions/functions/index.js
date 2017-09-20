const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);




exports.makeNotif = functions.database.ref('/matches/{matchId}')
    .onWrite(event => {
        var scoreFirstTeam = event.data.child('scoreFirstTeam').val();
        var scoreSecondTeam = event.data.child('scoreSecondTeam').val();

        console.log("Score first team : " + scoreFirstTeam);
        console.log("Score second team : " + scoreSecondTeam);

        if (scoreFirstTeam <= 0 || scoreSecondTeam <= 0) {
            var firstTeamFirstPlayerName = event.data.child('firstTeamFirstPlayerName').val();
            var firstTeamSecondPlayerName = event.data.child('firstTeamSecondPlayerName').val();
            var secondTeamFirstPlayerName = event.data.child('secondTeamFirstPlayerName').val();
            var secondTeamSecondPlayerName = event.data.child('secondTeamSecondPlayerName').val();
            var body;
            var firstTeamName;
            var firstTeamPlural;
            if (!firstTeamSecondPlayerName) {
                firstTeamName = firstTeamFirstPlayerName;
                firstTeamPlural = false;
            } else {
                firstTeamName = firstTeamFirstPlayerName + " & " + firstTeamSecondPlayerName;
                firstTeamPlural = true;
            }
            var secondTeamName;
            var secondTeamPlural;
            if (!secondTeamSecondPlayerName) {
                secondTeamName = secondTeamFirstPlayerName;
                secondTeamPlural = false;
            } else {
                secondTeamName = secondTeamFirstPlayerName + " & " + secondTeamSecondPlayerName;
                secondTeamPlural = true;
            }
            if (scoreFirstTeam <= 0) {
                var humiliationVerb = secondTeamPlural ? " ont humilié " : " a humilié ";
                body = secondTeamName + humiliationVerb + firstTeamName;
            } else {
                var humiliationVerb = firstTeamPlural ? " ont humilié " : " a humilié ";
                body = firstTeamName + humiliationVerb + secondTeamName;
            }


            var topic = "fanny";
            var payload = {
                notification: {
                    title: 'Une humiliation a eu lieu ...',
                    body: body,
                    icon: 'gs://baby-score.appspot.com/01_Fanny_1896.jpg'
                }
            };


            admin.messaging().sendToTopic(topic, payload)
                .then(function(response) {
                    console.log("Successfully sent message:", response);
                })
                .catch(function(error) {
                    console.log("Error sending message:", error);
                });
        }
    })
