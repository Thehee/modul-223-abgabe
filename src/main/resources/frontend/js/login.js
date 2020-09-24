import {getBearer, LOCALHOST_URL, setBearer} from './data.js';

const login = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const loginData = {};

    loginData['username'] = formData.get('username');
    loginData['password'] = formData.get('password');

    fetch(`${LOCALHOST_URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    }).then((result) => {
        setBearer(result.headers.get("Authorization"));
        console.log(getBearer());
    });
}

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('#login');
    loginForm.addEventListener('submit', login);
})