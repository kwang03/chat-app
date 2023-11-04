const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat-app'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/two', (greeting) => {
        showGreeting(JSON.parse(greeting.body));
    }, {id: $("#name").val()});
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.unsubscribe("/topic/two");
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    const input = $("#name").val();
    let json;
    if (input.startsWith("https")) {
        json = {'content': {'userid': null, 'imageUrl': input, 'language': 'EN'}};
    } else {
        json = {'content': {'userid': null, 'message': input, 'language': 'EN'}};
    }

    stompClient.publish({
        destination: "/app/two",
        body: JSON.stringify(json)
    });
}

function showGreeting(message) {
    console.log(message);
    const text = message.message;
    $("#greetings").append("<tr><td>" + message.content.userId + " (" + message.time + ")" + ": </td></tr>");
    if (text.startsWith("https")) {
        $("#greetings").append("<img src=" + text + "><img>");
    } else {
        $("#greetings").append("<tr><td>" + text + "</td></tr>");
    }

}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});

