import {LOCALHOST_URL, bearer, setBearer} from './data.js';

const login = (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);
    const loginData = {};

    loginData['username'] = formData.get('username');
    loginData['password'] = formData.get('password');

    fetch(`${LOCALHOST_URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData)
    }).then((result) => {
        setBearer(result.headers.get("Authorization"));
        console.log(bearer);
        fetchAllUser();
    });
}

const fetchAllUser = () => {
    fetch(`${LOCALHOST_URL}/user`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': bearer
        },
    }).then((userList) => {
        userList.json().then((userList) => {
            console.table(userList);
        });
    })
}

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('#login');
    loginForm.addEventListener('submit', login);
})