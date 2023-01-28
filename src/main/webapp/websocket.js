import websocket from 'websocket';

const ws = new websocket.w3cwebsocket('wss://quizlingo-backend.herokuapp.com/websocket');

ws.onopen = () => {
    const msg = {
        type: 'subscribe',
        channel: 'topic/interactions',
        interval: 500
    };
    ws.send(JSON.stringify(msg));
};

ws.onmessage = (event) => {
    console.log(event.data);
};