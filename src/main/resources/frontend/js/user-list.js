import {getBearer, LOCALHOST_URL} from "./data.js";

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
            renderCategories();
        });
    })
}

const renderCategories = () => {
    const display = document.querySelector('#userDisplay');
    display.innerHTML = '';

    users.forEach((user, key) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(user.id));
        row.appendChild(createCell(user.username));
        row.appendChild(createCell(user.role));
        display.appendChild(row);
    });
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

window.addEventListener('load', () => fetchAllUser());