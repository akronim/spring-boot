var alert = (function () {
    var alertDiv = document.getElementById("alert-div");
    var messageElement = document.getElementById('alert-msg');
    var closeBtn = document.getElementById("close-btn");

    function addEventListeners() {
        alertDiv.addEventListener("animationend", function () {
            resetAlertMessage();
        })

        closeBtn.addEventListener("click", function () {
            resetAlertMessage();
        })
    }

    function resetAlertMessage() {
        alertDiv
            .classList
            .remove("showHide");

        messageElement.innerHTML = "";
    }

    function setMessage(message) {
        messageElement.innerHTML = message;
    }

    function setShowHideClass() {
        alertDiv.classList.add("showHide");
    }

    function init(message) {
        if (alertDiv && message && message !== "${message}") {
            setMessage(message);
            addEventListeners();
            setShowHideClass();
        }
    }

    return {
        init: init
    };
}());

