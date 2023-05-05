let formEdit = document.forms["formEditUser"];
editUser();

async function editModalData(id) {
    const modal = new bootstrap.Modal(document.querySelector('#editModal'));
    modal.show();
    let user = await getUserId(id);
    formEdit.id.value = user.id;
    formEdit.username.value = user.username;
    formEdit.lastName.value = user.lastName;
    formEdit.age.value = user.age;
    formEdit.email.value = user.email;
    formEdit.roles.value = user.roles;
    loadRoles();
}

function editUser() {
    formEdit.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) editUserRoles.push({
                id: formEdit.roles.options[i].value,
                role: "ROLE_" + formEdit.roles.options[i].text
            });
        }
        fetch("http://localhost:8080/api/admin/" + formEdit.id.value + "/user", {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                username: formEdit.username.value,
                lastName: formEdit.lastName.value,
                age: formEdit.age.value,
                email: formEdit.email.value,
                password: formEdit.password.value,
                roles: editUserRoles
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            userTable();
        }).catch(error => console.error(error));
    });
}

function loadRoles() {
    let select = document.getElementById("roleEdit");
    select.innerHTML = "";

    fetch("http://localhost:8080/api/admin/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.role === "ROLE_USER" ? "USER" : role.role === "ROLE_ADMIN" ? "ADMIN" : role.name;
                select.appendChild(option);
            });
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRoles);