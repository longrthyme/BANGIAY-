$(document).ready(function () {
    $("#buttonCancel").on("click", function () {
        window.location = moduleURL;
    });

    $("#fileImage").change(function () {
        fileSize = this.files[0].size;
        if (fileSize > 1048576) {
            this.setCustomValidity("bạn phải chọn ảnh dưới 1MB");
            this.reportValidity();
        } else {
            this.setCustomValidity("");
            showImageThumbnail(this);
        }

    });
});

function showImageThumbnail(fileInput) {
    var file = fileInput.files[0];
    var reader = new FileReader();
    reader.onload = function (e) {
        $("#thumbnail").attr("src", e.target.result);
    };
    reader.readAsDataURL(file);
}

function showModalDialog(title, message) {
    $("#modalTitle").text(title);
    $("#modalBody").text(message);
    $("#modalDialog").modal('show');
}

$("#modalDialog .btn-danger, #modalDialog .close").on("click", function () {
    $("#modalDialog").modal("hide");
});

function showErrorModal(message) {
    showModalDialog("Error", message);
}

function showWarningModal(message) {
    showModalDialog("Warning", message);
}

function showThongBaoModal(message) {
    showModalDialog("Thông Báo", message);
}

// myScript.js
document.addEventListener("DOMContentLoaded", function () {
    setTimeout(function () {
        var successAlert = document.getElementById("success-alert");
        if (successAlert) {
            successAlert.style.display = "none";
        }
    }, 5000); // 5 giây (5000 milliseconds)
});

document.addEventListener("DOMContentLoaded", function () {
    setTimeout(function () {
        var warningAlert = document.getElementById("warning-alert");
        if (warningAlert) {
            warningAlert.style.display = "none";
        }
    }, 5000); // 5 giây (5000 milliseconds)
});

document.addEventListener("DOMContentLoaded", function () {
    var modal = document.getElementById("successModal");
    modal.style.display = "block";

    // Đóng modal khi người dùng nhấp vào nút đóng hoặc bất kỳ vị trí nào bên ngoài modal
    var closeBtn = document.getElementsByClassName("btn-close")[0];
    var closeModal = document.getElementById("dongModal");
    closeBtn.onclick = function () {
        modal.style.display = "none";
    };
    closeModal.onclick = function () {
        modal.style.display = "none";
    };
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
});
