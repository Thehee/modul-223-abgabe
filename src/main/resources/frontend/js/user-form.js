import {FRONTEND_URL, LOCALHOST_URL} from './data.js';

const createUser = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};

    user['username'] = formData.get('username');
    user['password'] = formData.get('password');

    fetch(`${LOCALHOST_URL}/user`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((res) => {
        if (res.ok) {
            window.location.href = FRONTEND_URL + "login.html";
        } else {
            console.log("There was an error. " + res.status);
        }
    });
}

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('#user-form');
    loginForm.addEventListener('submit', createUser);
})