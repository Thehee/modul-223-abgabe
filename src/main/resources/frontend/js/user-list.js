import {FRONTEND_URL, getBearer, LOCALHOST_URL} from "./data.js";

let users = [];

const fetchAllUser = () => {
    if (getBearer() === null) {
        alert("you need to login to access this function");
    }

    fetch(`${LOCALHOST_URL}/user`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        },
    }).then((userList) => {
        userList.json().then((userList) => {
            users = userList;
            renderUser();
        });
    })
}

const deleteUser = (id) => {
    fetch(`${LOCALHOST_URL}/user/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        }
    }).then(() => {
        fetchAllUser();
    });
}

const getOneUser = (id) => {
    fetch(`${LOCALHOST_URL}/user/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        }
    }).then((result) => {
        result.json().then((result) => {
            loadData(result);
        })
    })
}

const createUser = (user) => {
    fetch(`${LOCALHOST_URL}/user`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((res) => {
        if (res.ok) {
            resetForm();
            fetchAllUser();
        } else {
            console.log("There was an error. " + res.status);
        }
    });
}

const editUser = (user) => {
    fetch(`${LOCALHOST_URL}/user`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': getBearer()
        },
        body: JSON.stringify(user)
    }).then((res) => {
        if (res.ok) {
            resetForm();
            fetchAllUser();
        } else {
            console.log("There was an error. " + res.status);
        }
    });
}

const renderUser = () => {
    const display = document.querySelector('#userDisplay');
    display.innerHTML = '';

    users.forEach((user) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(user.id));
        row.appendChild(createCell(user.username));
        row.appendChild(createCell(user.role));

        let button = document.createElement('input');
        button.type = 'button';
        button.value = 'Delete';

        button.onclick = (e) =>  {
            e.stopPropagation();
            deleteUser(user.id);
        }
        row.appendChild(button);

        row.onclick = () => getOneUser(user.id);

        display.appendChild(row);
    });
};

const submitOnClick = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};

    user['username'] = formData.get('username');
    user['password'] = formData.get('password');
    let id = formData.get('id');

    if (id === '' || id === null) {
        createUser(user)
    } else {
        user['id'] = id;
        editUser(user);
    }
}

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const loadData = (user) => {
    document.getElementById("username").value = user.username;
    document.getElementById("password").value = "";
    document.getElementById("user-id").value = user.id;
    document.getElementById("submit-btn").value = "Update";
    document.getElementById("cancel-btn").style.display = "block";
}

const resetForm = () => {
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
    document.getElementById("user-id").value = "";
    document.getElementById("submit-btn").value = "Send";
    document.getElementById("cancel-btn").style.display = "none";
}

window.addEventListener('load', () => fetchAllUser());

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('#user-form');
    loginForm.addEventListener('submit', submitOnClick);
})

document.getElementById('cancel-btn').addEventListener('click', resetForm);