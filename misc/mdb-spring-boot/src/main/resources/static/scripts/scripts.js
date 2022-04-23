var messageModule = (function () {
    function notify(message) {
        alert(message);
    }

    return {
        notify: notify
    };
}());