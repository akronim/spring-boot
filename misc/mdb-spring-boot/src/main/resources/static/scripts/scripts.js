var messageModule = (function () {
    var alertDiv = document.getElementById("alert-div");
    var messageElement = document.getElementById('alert-msg');
    var closeBtn = document.querySelector(".alert-item-close");

    function addEventListeners() {
        closeBtn.addEventListener("click", function () {
            resetAlertMessage();
        })
    }

    function resetAlertMessage() {
        alertDiv
            .classList
            .add("closed");

        messageElement.innerHTML = "";
    }

    function setMessage(message) {
        messageElement.innerHTML = message;
    }

    function setShowHideClass() {
        alertDiv.classList.remove("closed");
    }

    function init(message) {
        if (alertDiv && message) {
            setMessage(message);
            addEventListeners();
            setShowHideClass();
        }
    }

    return {
        init: init
    };
}());