document.addEventListener("DOMContentLoaded", startValidation);

function startValidation() {
  var form = document.querySelector("#registerForm");

  if (!form) {
    return;
  }

  var statusBox = document.querySelector("#formStatus");

  var fields = [
    {
      name: "nom",
      inputSelector: "#nom",
      errorSelector: "#nomError"
    },
    {
      name: "prenom",
      inputSelector: "#prenom",
      errorSelector: "#prenomError"
    },
    {
      name: "email",
      inputSelector: "#email",
      errorSelector: "#emailError"
    },
    {
      name: "password",
      inputSelector: "#password",
      errorSelector: "#passwordError"
    },
    {
      name: "confirm_password",
      inputSelector: "#confirm_password",
      errorSelector: "#confirmPasswordError"
    },
    {
      name: "telephone",
      inputSelector: "#telephone",
      errorSelector: "#telephoneError"
    }
  ];

  function setStatus(type, msg) {
    if (!statusBox) {
      return;
    }

    if (!msg) {
      statusBox.className = "alert d-none";
      statusBox.textContent = "";
      return;
    }

    statusBox.className = "alert alert-" + type;
    statusBox.textContent = msg;
  }

  function clearFeedback() {
    var i;

    for (i = 0; i < fields.length; i++) {
      var input = document.querySelector(fields[i].inputSelector);
      var err = document.querySelector(fields[i].errorSelector);

      input.classList.remove("is-invalid", "is-valid");

      if (err) {
        err.textContent = "";
      }
    }

    setStatus(null, "");
  }

  function applyServerResult(data) {
    if (data.values && data.values.telephone) {
      document.querySelector("#telephone").value = data.values.telephone;
    }

    var i;

    for (i = 0; i < fields.length; i++) {
      var field = fields[i];
      var input = document.querySelector(field.inputSelector);
      var err = document.querySelector(field.errorSelector);
      var msg = "";

      if (data.errors && data.errors[field.name]) {
        msg = data.errors[field.name];
      }

      if (msg) {
        input.classList.add("is-invalid");
        input.classList.remove("is-valid");

        if (err) {
          err.textContent = msg;
        }
      } else {
        input.classList.remove("is-invalid");
        input.classList.add("is-valid");

        if (err) {
          err.textContent = "";
        }
      }
    }

    if (data.errors && data.errors._global) {
      setStatus("warning", data.errors._global);
    }
  }

  function callValidate(successCallback, errorCallback) {
    var request = new XMLHttpRequest();
    var formData = new FormData(form);

    request.open("POST", "/api/validate/register");
    request.setRequestHeader("X-Requested-With", "XMLHttpRequest");

    request.onload = function () {
      var data;

      if (request.status < 200 || request.status >= 300) {
        errorCallback("Erreur serveur lors de la validation.");
        return;
      }

      try {
        data = JSON.parse(request.responseText);
        successCallback(data);
      } catch (error) {
        errorCallback("Réponse serveur invalide.");
      }
    };

    request.onerror = function () {
      errorCallback("Une erreur est survenue.");
    };

    request.send(formData);
  }

  form.addEventListener("submit", function (event) {
    event.preventDefault();
    clearFeedback();

    callValidate(
      function (data) {
        applyServerResult(data);

        if (data.ok) {
          setStatus("success", "Validation OK ✅ Envoi en cours...");
          form.submit();
        } else {
          setStatus("danger", "Veuillez corriger les erreurs.");
        }
      },
      function (message) {
        setStatus("warning", message);
      }
    );
  });

  function validateWhenLeavingInput() {
    callValidate(
      function (data) {
        applyServerResult(data);
      },
      function (message) {
        setStatus("warning", message);
      }
    );
  }

  var i;

  for (i = 0; i < fields.length; i++) {
    var input = document.querySelector(fields[i].inputSelector);

    input.addEventListener("blur", function () {
      validateWhenLeavingInput();
    });
  }
}
