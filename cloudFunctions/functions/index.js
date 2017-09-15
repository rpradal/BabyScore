@const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);




exports.makeNotif = functions.database.ref('/matches')
    .onWrite(event => {
        console.log('Make Notif')
        var topic = "fanny";

        var payload = {
            notification: {
                title: 'Une humiliation a eu lieu ...',
                body: 'FANNY 2!!!',
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
    })
