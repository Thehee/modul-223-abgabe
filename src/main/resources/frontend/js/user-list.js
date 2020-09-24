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

const deleteEntry = (id) => {
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


const renderCategories = () => {
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

        button.onclick = () => deleteEntry(user.id);

        row.appendChild(button);

        display.appendChild(row);
    });
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

window.addEventListener('load', () => fetchAllUser());